.class public Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;
.super Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/a;
.source "WrapMediaEncoder.java"


# instance fields
.field private a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

.field private b:Landroid/media/MediaCodec;

.field private c:[B

.field private d:[B

.field private e:[Ljava/nio/ByteBuffer;

.field private f:[Ljava/nio/ByteBuffer;


# direct methods
.method public constructor <init>()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    invoke-direct {p0}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/a;-><init>()V

    const-string v0, "video/avc"

    invoke-static {v0}, Landroid/media/MediaCodec;->createEncoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object v1

    iput-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const/16 v1, 0x500

    const/16 v2, 0x2d0

    invoke-static {v0, v1, v2}, Landroid/media/MediaFormat;->createVideoFormat(Ljava/lang/String;II)Landroid/media/MediaFormat;

    move-result-object v0

    const-string v1, "bitrate"

    const v2, 0x1e848

    invoke-virtual {v0, v1, v2}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string v1, "frame-rate"

    const/16 v2, 0x1e

    invoke-virtual {v0, v1, v2}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string v1, "color-format"

    const/16 v2, 0x15

    invoke-virtual {v0, v1, v2}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string v1, "i-frame-interval"

    const/4 v2, 0x5

    invoke-virtual {v0, v1, v2}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    iget-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const/4 v2, 0x0

    const/4 v3, 0x1

    invoke-virtual {v1, v0, v2, v2, v3}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->start()V

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->getInputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->e:[Ljava/nio/ByteBuffer;

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->f:[Ljava/nio/ByteBuffer;

    return-void
.end method

.method public constructor <init>(II)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    invoke-direct {p0}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/a;-><init>()V

    const-string v0, "video/avc"

    invoke-static {v0}, Landroid/media/MediaCodec;->createEncoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object v1

    iput-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-static {v0, p1, p2}, Landroid/media/MediaFormat;->createVideoFormat(Ljava/lang/String;II)Landroid/media/MediaFormat;

    move-result-object p1

    const-string p2, "bitrate"

    const v0, 0x1e848

    invoke-virtual {p1, p2, v0}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string p2, "frame-rate"

    const/16 v0, 0x1e

    invoke-virtual {p1, p2, v0}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string p2, "color-format"

    const/16 v0, 0x15

    invoke-virtual {p1, p2, v0}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    const-string p2, "i-frame-interval"

    const/4 v0, 0x5

    invoke-virtual {p1, p2, v0}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    iget-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const/4 v0, 0x0

    const/4 v1, 0x1

    invoke-virtual {p2, p1, v0, v0, v1}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    iget-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {p1}, Landroid/media/MediaCodec;->start()V

    iget-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {p1}, Landroid/media/MediaCodec;->getInputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object p1

    iput-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->e:[Ljava/nio/ByteBuffer;

    iget-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {p1}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object p1

    iput-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->f:[Ljava/nio/ByteBuffer;

    return-void
.end method


# virtual methods
.method public a()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->stop()V

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->release()V

    return-void
.end method

.method public a(Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;)V
    .locals 0

    iput-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    return-void
.end method

.method public a([BJ)Z
    .locals 19

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    iget-object v2, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const-string v3, "WrapMediaEncoder"

    const/4 v4, 0x0

    if-eqz v2, :cond_e

    iget-object v5, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->e:[Ljava/nio/ByteBuffer;

    if-eqz v5, :cond_e

    iget-object v5, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->f:[Ljava/nio/ByteBuffer;

    if-eqz v5, :cond_e

    iget-object v5, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    if-nez v5, :cond_0

    goto/16 :goto_6

    :cond_0
    invoke-virtual {v2}, Landroid/media/MediaCodec;->getInputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v2

    iget-object v5, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v5}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v5

    iget-object v6, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const-wide/16 v7, -0x1

    invoke-virtual {v6, v7, v8}, Landroid/media/MediaCodec;->dequeueInputBuffer(J)I

    move-result v10

    if-ltz v10, :cond_2

    aget-object v2, v2, v10

    invoke-virtual {v2}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    if-eqz v1, :cond_1

    invoke-virtual {v2, v1}, Ljava/nio/ByteBuffer;->put([B)Ljava/nio/ByteBuffer;

    iget-object v9, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const/4 v11, 0x0

    array-length v12, v1

    const/4 v15, 0x0

    move-wide/from16 v13, p2

    invoke-virtual/range {v9 .. v15}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    goto :goto_0

    :cond_1
    iget-object v9, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v15, 0x4

    move-wide/from16 v13, p2

    invoke-virtual/range {v9 .. v15}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    :cond_2
    :goto_0
    new-instance v1, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v1}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    iget-object v2, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    const-wide/16 v6, 0x2710

    invoke-virtual {v2, v1, v6, v7}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v2

    const/4 v8, -0x3

    const/4 v9, 0x1

    if-eq v2, v8, :cond_d

    const/4 v8, -0x2

    const/4 v10, 0x2

    if-eq v2, v8, :cond_c

    const/4 v8, -0x1

    const/4 v11, 0x3

    if-eq v2, v8, :cond_b

    :goto_1
    if-ltz v2, :cond_a

    iget v8, v1, Landroid/media/MediaCodec$BufferInfo;->flags:I

    const/4 v12, 0x4

    and-int/2addr v8, v12

    if-eqz v8, :cond_3

    const-string v8, "OutputBuffer BUFFER_FLAG_END_OF_STREAM"

    invoke-static {v3, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v8, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    invoke-interface {v8, v12}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    :cond_3
    aget-object v8, v5, v2

    if-nez v8, :cond_4

    const-string v1, "Output buffer is null!"

    invoke-static {v3, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_3

    :cond_4
    iget v13, v1, Landroid/media/MediaCodec$BufferInfo;->size:I

    if-gtz v13, :cond_5

    const-string v1, "Output was not available!"

    invoke-static {v3, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_3

    :cond_5
    new-array v13, v10, [Ljava/lang/Object;

    iget v14, v1, Landroid/media/MediaCodec$BufferInfo;->flags:I

    invoke-static {v14}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v14

    aput-object v14, v13, v4

    iget v14, v1, Landroid/media/MediaCodec$BufferInfo;->size:I

    invoke-static {v14}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v14

    aput-object v14, v13, v9

    const-string v14, "Output was available Falg:%d Size:%d"

    invoke-static {v14, v13}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v13

    invoke-static {v3, v13}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget v13, v1, Landroid/media/MediaCodec$BufferInfo;->offset:I

    invoke-virtual {v8, v13}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    iget v13, v1, Landroid/media/MediaCodec$BufferInfo;->offset:I

    iget v14, v1, Landroid/media/MediaCodec$BufferInfo;->size:I

    add-int/2addr v13, v14

    invoke-virtual {v8, v13}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    iget v13, v1, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/2addr v13, v10

    if-eqz v13, :cond_9

    const-string v13, "OutputBuffer BUFFER_FLAG_CODEC_CONFIG"

    invoke-static {v3, v13}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget v13, v1, Landroid/media/MediaCodec$BufferInfo;->size:I

    new-array v13, v13, [B

    invoke-virtual {v8, v13}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    invoke-static {v13}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object v14

    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->getInt()I

    move-result v8

    if-ne v8, v9, :cond_6

    sget-object v8, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v15, "parsing sps/pps"

    invoke-virtual {v8, v15}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    goto :goto_2

    :cond_6
    sget-object v8, Ljava/lang/System;->out:Ljava/io/PrintStream;

    const-string v15, "something is amiss?"

    invoke-virtual {v8, v15}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    :cond_7
    :goto_2
    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->get()B

    move-result v8

    if-nez v8, :cond_7

    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->get()B

    move-result v8

    if-nez v8, :cond_7

    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->get()B

    move-result v8

    if-nez v8, :cond_7

    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->get()B

    move-result v8

    if-eq v8, v9, :cond_8

    goto :goto_2

    :cond_8
    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->position()I

    move-result v8

    add-int/lit8 v14, v8, -0x8

    add-int/2addr v14, v12

    new-array v14, v14, [B

    iput-object v14, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->c:[B

    iget-object v14, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->c:[B

    aput-byte v4, v14, v4

    aput-byte v4, v14, v9

    aput-byte v4, v14, v10

    aput-byte v9, v14, v11

    array-length v15, v14

    sub-int/2addr v15, v12

    invoke-static {v13, v12, v14, v12, v15}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    array-length v14, v13

    sub-int/2addr v14, v8

    add-int/2addr v14, v12

    new-array v14, v14, [B

    iput-object v14, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->d:[B

    iget-object v14, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->d:[B

    aput-byte v4, v14, v4

    aput-byte v4, v14, v9

    aput-byte v4, v14, v10

    aput-byte v9, v14, v11

    array-length v15, v14

    sub-int/2addr v15, v12

    invoke-static {v13, v8, v14, v12, v15}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget-object v8, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    iget-object v12, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->c:[B

    iget-object v13, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->d:[B

    invoke-interface {v8, v12, v13}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a([B[B)Z

    iget-object v8, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v8, v2, v4}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    iget-object v2, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v2, v1, v6, v7}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v2

    goto/16 :goto_1

    :cond_9
    iget v12, v1, Landroid/media/MediaCodec$BufferInfo;->size:I

    new-array v14, v12, [B

    invoke-virtual {v8, v14}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    iget-object v13, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    const/4 v15, 0x0

    array-length v8, v14

    iget-wide v9, v1, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    move/from16 v16, v8

    move-wide/from16 v17, v9

    invoke-interface/range {v13 .. v18}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a([BIIJ)Z

    iget-object v8, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v8, v2, v4}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    iget-object v2, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v2, v1, v6, v7}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v2

    const/4 v9, 0x1

    const/4 v10, 0x2

    goto/16 :goto_1

    :cond_a
    :goto_3
    move v2, v9

    goto :goto_5

    :cond_b
    const-string v1, "dequeueOutputBuffer timed out!"

    invoke-static {v3, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v1, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    invoke-interface {v1, v11}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    goto :goto_4

    :cond_c
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "New format "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v2}, Landroid/media/MediaCodec;->getOutputFormat()Landroid/media/MediaFormat;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v3, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v1, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    const/4 v2, 0x2

    invoke-interface {v1, v2}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    :goto_4
    const/4 v2, 0x1

    goto :goto_5

    :cond_d
    const-string v1, "INFO_OUTPUT_BUFFERS_CHANGED"

    invoke-static {v3, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v1, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->b:Landroid/media/MediaCodec;

    invoke-virtual {v1}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    iget-object v1, v0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/c;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    const/4 v2, 0x1

    invoke-interface {v1, v2}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    :goto_5
    return v2

    :cond_e
    :goto_6
    const-string v1, "Media codec did not initailize"

    invoke-static {v3, v1}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    return v4
.end method
