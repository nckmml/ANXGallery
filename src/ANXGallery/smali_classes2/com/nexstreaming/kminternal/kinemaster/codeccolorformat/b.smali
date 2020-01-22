.class public Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;
.super Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/a;
.source "WrapMediaDecoder.java"


# instance fields
.field private a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

.field private b:I

.field private c:Landroid/media/MediaCodec;

.field private d:Landroid/view/Surface;

.field private e:Landroid/media/MediaFormat;

.field private f:I

.field private g:I

.field private h:[Ljava/nio/ByteBuffer;

.field private i:[Ljava/nio/ByteBuffer;


# direct methods
.method public constructor <init>(Landroid/media/MediaFormat;Landroid/view/Surface;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    invoke-direct {p0}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/a;-><init>()V

    const/4 v0, 0x1

    iput v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b:I

    const-string v0, "mime"

    invoke-virtual {p1, v0}, Landroid/media/MediaFormat;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/media/MediaCodec;->createDecoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object v1

    iput-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    iget-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    if-nez v1, :cond_0

    const-string p1, "WrapMediaDecoder"

    const-string p2, "Can\'t create media codec!"

    invoke-static {p1, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_0
    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual {v1, p1, p2, v2, v3}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    const-string v1, "video/"

    invoke-virtual {v0, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iput-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->d:Landroid/view/Surface;

    iput v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b:I

    :cond_1
    iget-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {p2}, Landroid/media/MediaCodec;->start()V

    iget-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {p2}, Landroid/media/MediaCodec;->getInputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object p2

    iput-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->h:[Ljava/nio/ByteBuffer;

    iget-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {p2}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object p2

    iput-object p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->i:[Ljava/nio/ByteBuffer;

    const-string p2, "width"

    invoke-virtual {p1, p2}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result p2

    iput p2, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->f:I

    const-string p2, "height"

    invoke-virtual {p1, p2}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result p1

    iput p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->g:I

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

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->stop()V

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->release()V

    return-void
.end method

.method public a(Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;)V
    .locals 0

    iput-object p1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    return-void
.end method

.method public a(Ljava/nio/ByteBuffer;J)Z
    .locals 12

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v1, 0x0

    const-string v2, "WrapMediaDecoder"

    if-eqz v0, :cond_3

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->h:[Ljava/nio/ByteBuffer;

    if-eqz v3, :cond_3

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->i:[Ljava/nio/ByteBuffer;

    if-eqz v3, :cond_3

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    if-nez v3, :cond_0

    goto :goto_1

    :cond_0
    const-wide/16 v3, 0x2710

    invoke-virtual {v0, v3, v4}, Landroid/media/MediaCodec;->dequeueInputBuffer(J)I

    move-result v6

    if-ltz v6, :cond_2

    if-nez p1, :cond_1

    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v11, 0x4

    move-wide v9, p2

    invoke-virtual/range {v5 .. v11}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    new-array p1, v1, [Ljava/lang/Object;

    const-string p2, "WrapMediaDecoder EOS pass"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->h:[Ljava/nio/ByteBuffer;

    aget-object v0, v0, v6

    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    invoke-virtual {v0, p1}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;

    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v7, 0x0

    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->limit()I

    move-result v8

    const/4 v11, 0x0

    move-wide v9, p2

    invoke-virtual/range {v5 .. v11}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    new-array p1, v1, [Ljava/lang/Object;

    const-string p2, "WrapMediaDecoder one frame pass"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    :goto_0
    invoke-virtual {p0}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b()Z

    const/4 p1, 0x1

    return p1

    :cond_3
    :goto_1
    const-string p1, "Media codec did not initailize"

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v1
.end method

.method public a([BJ)Z
    .locals 12

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v1, 0x0

    const-string v2, "WrapMediaDecoder"

    if-eqz v0, :cond_4

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->h:[Ljava/nio/ByteBuffer;

    if-eqz v3, :cond_4

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->i:[Ljava/nio/ByteBuffer;

    if-eqz v3, :cond_4

    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    if-nez v3, :cond_0

    goto :goto_2

    :cond_0
    const-wide/16 v3, -0x1

    invoke-virtual {v0, v3, v4}, Landroid/media/MediaCodec;->dequeueInputBuffer(J)I

    move-result v6

    const/4 v0, 0x1

    if-ltz v6, :cond_3

    if-eqz p1, :cond_2

    array-length v3, p1

    if-gtz v3, :cond_1

    goto :goto_0

    :cond_1
    iget-object v3, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->h:[Ljava/nio/ByteBuffer;

    aget-object v3, v3, v6

    invoke-virtual {v3}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    invoke-virtual {v3, p1}, Ljava/nio/ByteBuffer;->put([B)Ljava/nio/ByteBuffer;

    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v7, 0x0

    array-length v8, p1

    const/4 v11, 0x0

    move-wide v9, p2

    invoke-virtual/range {v5 .. v11}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    new-array p1, v0, [Ljava/lang/Object;

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    aput-object p2, p1, v1

    const-string p2, "WrapMediaDecoder one frame pass time(%d)"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    :cond_2
    :goto_0
    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v11, 0x4

    move-wide v9, p2

    invoke-virtual/range {v5 .. v11}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    new-array p1, v1, [Ljava/lang/Object;

    const-string p2, "WrapMediaDecoder EOS pass"

    invoke-static {p2, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_3
    :goto_1
    invoke-virtual {p0}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b()Z

    return v0

    :cond_4
    :goto_2
    const-string p1, "Media codec did not initailize"

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v1
.end method

.method public b()Z
    .locals 15

    new-instance v0, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v0}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    iget-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    const-wide/16 v2, 0x2710

    invoke-virtual {v1, v0, v2, v3}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v1

    const/4 v4, 0x1

    new-array v5, v4, [Ljava/lang/Object;

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    const/4 v7, 0x0

    aput-object v6, v5, v7

    const-string v6, "OutputBuffer outIndex(%d)"

    invoke-static {v6, v5}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    const-string v6, "WrapMediaDecoder"

    invoke-static {v6, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v5, -0x3

    if-eq v1, v5, :cond_7

    const/4 v5, -0x2

    if-eq v1, v5, :cond_5

    const/4 v5, -0x1

    if-eq v1, v5, :cond_4

    :goto_0
    if-ltz v1, :cond_8

    iget v5, v0, Landroid/media/MediaCodec$BufferInfo;->flags:I

    const/4 v8, 0x4

    and-int/2addr v5, v8

    if-eqz v5, :cond_0

    const-string v5, "OutputBuffer BUFFER_FLAG_END_OF_STREAM"

    invoke-static {v6, v5}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    invoke-interface {v5, v8}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    :cond_0
    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->i:[Ljava/nio/ByteBuffer;

    aget-object v5, v5, v1

    if-nez v5, :cond_1

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0, v1, v4}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    const-string v0, "OutputBuffer was not available"

    invoke-static {v6, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_2

    :cond_1
    iget v8, v0, Landroid/media/MediaCodec$BufferInfo;->size:I

    if-gtz v8, :cond_2

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0, v1, v4}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    const-string v0, "Output was not available!"

    invoke-static {v6, v0}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_2

    :cond_2
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "Can\'t use; render anyway, "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v6, v8}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v8, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->d:Landroid/view/Surface;

    if-eqz v8, :cond_3

    iget-object v5, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    iget v8, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b:I

    iget-wide v9, v0, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    invoke-interface {v5, v8, v9, v10}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(IJ)Z

    move-result v5

    goto :goto_1

    :cond_3
    iget v8, v0, Landroid/media/MediaCodec$BufferInfo;->size:I

    new-array v10, v8, [B

    invoke-virtual {v5, v10}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    invoke-virtual {v5, v7}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    iget-object v9, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    iget v11, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->b:I

    array-length v12, v10

    iget-wide v13, v0, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    invoke-interface/range {v9 .. v14}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a([BIIJ)Z

    move-result v5

    :goto_1
    iget-object v8, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v8, v1, v5}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    iget-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v1, v0, v2, v3}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v1

    goto :goto_0

    :cond_4
    const-string v0, "dequeueOutputBuffer timed out!"

    invoke-static {v6, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    const/4 v1, 0x3

    invoke-interface {v0, v1}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    goto :goto_2

    :cond_5
    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->getOutputFormat()Landroid/media/MediaFormat;

    move-result-object v0

    iput-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->e:Landroid/media/MediaFormat;

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->e:Landroid/media/MediaFormat;

    if-eqz v0, :cond_6

    const-string v1, "width"

    invoke-virtual {v0, v1}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->f:I

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->e:Landroid/media/MediaFormat;

    const-string v1, "height"

    invoke-virtual {v0, v1}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->g:I

    :cond_6
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "New format "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->e:Landroid/media/MediaFormat;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v6, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    const/4 v1, 0x2

    invoke-interface {v0, v1}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    goto :goto_2

    :cond_7
    const-string v0, "INFO_OUTPUT_BUFFERS_CHANGED"

    invoke-static {v6, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->c:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->getOutputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v0

    iput-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->i:[Ljava/nio/ByteBuffer;

    iget-object v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->a:Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;

    invoke-interface {v0, v4}, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/d;->a(I)Z

    :cond_8
    :goto_2
    return v4
.end method

.method public c()I
    .locals 1

    iget v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->f:I

    return v0
.end method

.method public d()I
    .locals 1

    iget v0, p0, Lcom/nexstreaming/kminternal/kinemaster/codeccolorformat/b;->g:I

    return v0
.end method
