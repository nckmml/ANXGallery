.class public Lcom/miui/gallery/util/XmpHelper;
.super Ljava/lang/Object;
.source "XmpHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/util/XmpHelper$Section;
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "XmpHelper"


# direct methods
.method static constructor <clinit>()V
    .locals 4

    :try_start_0
    invoke-static {}, Lcom/adobe/xmp/XMPMetaFactory;->getSchemaRegistry()Lcom/adobe/xmp/XMPSchemaRegistry;

    move-result-object v0

    const-string v1, "http://ns.google.com/photos/1.0/camera/"

    const-string v2, "GCamera"

    invoke-interface {v0, v1, v2}, Lcom/adobe/xmp/XMPSchemaRegistry;->registerNamespace(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    invoke-static {}, Lcom/adobe/xmp/XMPMetaFactory;->getSchemaRegistry()Lcom/adobe/xmp/XMPSchemaRegistry;

    move-result-object v0

    const-string v1, "http://ns.xiaomi.com/photos/1.0/camera/"

    const-string v2, "MiCamera"

    invoke-interface {v0, v1, v2}, Lcom/adobe/xmp/XMPSchemaRegistry;->registerNamespace(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    :try_end_0
    .catch Lcom/adobe/xmp/XMPException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    sget-object v1, Lcom/miui/gallery/util/XmpHelper;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Failed to register ns http://ns.google.com/photos/1.0/camera/: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static extractXMPMeta(Ljava/io/InputStream;)Lcom/adobe/xmp/XMPMeta;
    .locals 5

    const/4 v0, 0x1

    invoke-static {p0, v0}, Lcom/miui/gallery/util/XmpHelper;->parse(Ljava/io/InputStream;Z)Ljava/util/List;

    move-result-object p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    :cond_0
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/util/XmpHelper$Section;

    iget-object v2, v1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    invoke-static {v2}, Lcom/miui/gallery/util/XmpHelper;->hasXMPHeader([B)Z

    move-result v2

    if-eqz v2, :cond_1

    iget-object p0, v1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    invoke-static {p0}, Lcom/miui/gallery/util/XmpHelper;->getXMPContentEnd([B)I

    move-result p0

    const/16 v2, 0x1d

    sub-int/2addr p0, v2

    new-array p0, p0, [B

    iget-object v1, v1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    const/4 v3, 0x0

    array-length v4, p0

    invoke-static {v1, v2, p0, v3, v4}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    :try_start_0
    invoke-static {p0}, Lcom/adobe/xmp/XMPMetaFactory;->parseFromBuffer([B)Lcom/adobe/xmp/XMPMeta;

    move-result-object p0
    :try_end_0
    .catch Lcom/adobe/xmp/XMPException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    sget-object v1, Lcom/miui/gallery/util/XmpHelper;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "XMP parse error: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {v1, p0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    return-object v0
.end method

.method private static getXMPContentEnd([B)I
    .locals 4

    array-length v0, p0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    :goto_0
    if-lt v0, v1, :cond_1

    aget-byte v2, p0, v0

    const/16 v3, 0x3e

    if-ne v2, v3, :cond_0

    add-int/lit8 v2, v0, -0x1

    aget-byte v2, p0, v2

    const/16 v3, 0x3f

    if-eq v2, v3, :cond_0

    add-int/2addr v0, v1

    return v0

    :cond_0
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_1
    array-length p0, p0

    return p0
.end method

.method private static hasXMPHeader([B)Z
    .locals 3

    array-length v0, p0

    const/16 v1, 0x1d

    const/4 v2, 0x0

    if-ge v0, v1, :cond_0

    return v2

    :cond_0
    :try_start_0
    new-array v0, v1, [B

    invoke-static {p0, v2, v0, v2, v1}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    new-instance p0, Ljava/lang/String;

    const-string v1, "UTF-8"

    invoke-direct {p0, v0, v1}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    const-string v0, "http://ns.adobe.com/xap/1.0/\u0000"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :catch_0
    :cond_1
    return v2
.end method

.method private static parse(Ljava/io/InputStream;Z)Ljava/util/List;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/InputStream;",
            "Z)",
            "Ljava/util/List<",
            "Lcom/miui/gallery/util/XmpHelper$Section;",
            ">;"
        }
    .end annotation

    const/4 v0, 0x0

    :try_start_0
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v1

    const/16 v2, 0xff

    if-ne v1, v2, :cond_10

    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v1

    const/16 v3, 0xd8

    if-eq v1, v3, :cond_0

    goto/16 :goto_4

    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    :goto_0
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v3
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_6
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v4, -0x1

    if-eq v3, v4, :cond_e

    if-eq v3, v2, :cond_2

    if-eqz p0, :cond_1

    :try_start_1
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    :catch_0
    :cond_1
    return-object v0

    :cond_2
    :goto_1
    :try_start_2
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v3
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_6
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-ne v3, v2, :cond_3

    goto :goto_1

    :cond_3
    if-ne v3, v4, :cond_5

    if-eqz p0, :cond_4

    :try_start_3
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_1

    :catch_1
    :cond_4
    return-object v0

    :cond_5
    const/16 v5, 0xda

    const/4 v6, 0x0

    if-ne v3, v5, :cond_8

    if-nez p1, :cond_6

    :try_start_4
    new-instance p1, Lcom/miui/gallery/util/XmpHelper$Section;

    invoke-direct {p1, v0}, Lcom/miui/gallery/util/XmpHelper$Section;-><init>(Lcom/miui/gallery/util/XmpHelper$1;)V

    iput v3, p1, Lcom/miui/gallery/util/XmpHelper$Section;->marker:I

    iput v4, p1, Lcom/miui/gallery/util/XmpHelper$Section;->length:I

    invoke-virtual {p0}, Ljava/io/InputStream;->available()I

    move-result v2

    new-array v2, v2, [B

    iput-object v2, p1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    iget-object v2, p1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    iget-object v3, p1, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    array-length v3, v3

    invoke-virtual {p0, v2, v6, v3}, Ljava/io/InputStream;->read([BII)I

    invoke-interface {v1, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_6
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    :cond_6
    if-eqz p0, :cond_7

    :try_start_5
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_2

    :catch_2
    :cond_7
    return-object v1

    :cond_8
    :try_start_6
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v5

    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v7

    if-eq v5, v4, :cond_c

    if-ne v7, v4, :cond_9

    goto :goto_3

    :cond_9
    shl-int/lit8 v4, v5, 0x8

    or-int/2addr v4, v7

    if-eqz p1, :cond_b

    const/16 v5, 0xe1

    if-ne v3, v5, :cond_a

    goto :goto_2

    :cond_a
    add-int/lit8 v4, v4, -0x2

    int-to-long v3, v4

    invoke-virtual {p0, v3, v4}, Ljava/io/InputStream;->skip(J)J

    goto :goto_0

    :cond_b
    :goto_2
    new-instance v5, Lcom/miui/gallery/util/XmpHelper$Section;

    invoke-direct {v5, v0}, Lcom/miui/gallery/util/XmpHelper$Section;-><init>(Lcom/miui/gallery/util/XmpHelper$1;)V

    iput v3, v5, Lcom/miui/gallery/util/XmpHelper$Section;->marker:I

    iput v4, v5, Lcom/miui/gallery/util/XmpHelper$Section;->length:I

    add-int/lit8 v4, v4, -0x2

    new-array v3, v4, [B

    iput-object v3, v5, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    iget-object v3, v5, Lcom/miui/gallery/util/XmpHelper$Section;->data:[B

    invoke-virtual {p0, v3, v6, v4}, Ljava/io/InputStream;->read([BII)I

    invoke-interface {v1, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    goto :goto_0

    :cond_c
    :goto_3
    if-eqz p0, :cond_d

    :try_start_7
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_3

    :catch_3
    :cond_d
    return-object v0

    :cond_e
    if-eqz p0, :cond_f

    :try_start_8
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_4

    :catch_4
    :cond_f
    return-object v1

    :cond_10
    :goto_4
    if-eqz p0, :cond_11

    :try_start_9
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_9
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_9} :catch_5

    :catch_5
    :cond_11
    return-object v0

    :catchall_0
    move-exception p1

    goto :goto_5

    :catch_6
    move-exception p1

    :try_start_a
    sget-object v1, Lcom/miui/gallery/util/XmpHelper;->TAG:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Could not parse file: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_0

    if-eqz p0, :cond_12

    :try_start_b
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_b
    .catch Ljava/io/IOException; {:try_start_b .. :try_end_b} :catch_7

    :catch_7
    :cond_12
    return-object v0

    :goto_5
    if-eqz p0, :cond_13

    :try_start_c
    invoke-virtual {p0}, Ljava/io/InputStream;->close()V
    :try_end_c
    .catch Ljava/io/IOException; {:try_start_c .. :try_end_c} :catch_8

    :catch_8
    :cond_13
    throw p1
.end method

.method public static readMicroVideoOffset(Ljava/lang/String;)J
    .locals 10

    const-string v0, "http://ns.google.com/photos/1.0/camera/"

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    const-wide/16 v2, 0x0

    if-eqz v1, :cond_0

    return-wide v2

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    const/4 v1, 0x0

    :try_start_0
    new-instance v6, Ljava/io/File;

    invoke-direct {v6, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance p0, Ljava/io/FileInputStream;

    invoke-direct {p0, v6}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    invoke-static {p0}, Lcom/miui/gallery/util/XmpHelper;->extractXMPMeta(Ljava/io/InputStream;)Lcom/adobe/xmp/XMPMeta;

    move-result-object v1
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-nez v1, :cond_1

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-wide v2

    :cond_1
    :try_start_2
    const-string v7, "MicroVideo"

    invoke-interface {v1, v0, v7}, Lcom/adobe/xmp/XMPMeta;->getPropertyInteger(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v7

    if-eqz v7, :cond_4

    invoke-virtual {v7}, Ljava/lang/Integer;->intValue()I

    move-result v7

    const/4 v8, 0x1

    if-eq v7, v8, :cond_2

    goto :goto_0

    :cond_2
    const-string v7, "MicroVideoOffset"

    invoke-interface {v1, v0, v7}, Lcom/adobe/xmp/XMPMeta;->getPropertyInteger(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    if-eqz v0, :cond_3

    sget-object v1, Lcom/miui/gallery/util/XmpHelper;->TAG:Ljava/lang/String;

    const-string v7, "readMicroVideoOffset cost %s"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v8

    sub-long/2addr v8, v4

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    invoke-static {v1, v7, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v6}, Ljava/io/File;->length()J

    move-result-wide v4

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    int-to-long v0, v0

    sub-long/2addr v4, v0

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-wide v4

    :cond_3
    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    goto :goto_2

    :cond_4
    :goto_0
    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    return-wide v2

    :catchall_0
    move-exception v0

    goto :goto_3

    :catch_0
    move-exception v0

    move-object v1, p0

    goto :goto_1

    :catchall_1
    move-exception v0

    move-object p0, v1

    goto :goto_3

    :catch_1
    move-exception v0

    :goto_1
    :try_start_3
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    invoke-static {v1}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    :goto_2
    return-wide v2

    :goto_3
    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->closeSilently(Ljava/io/Closeable;)V

    throw v0
.end method
