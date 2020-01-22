.class public Lcom/cdv/io/NvAndroidVideoFileReader;
.super Ljava/lang/Object;
.source "NvAndroidVideoFileReader.java"

# interfaces
.implements Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;


# static fields
.field private static final ERROR_EOF:I = 0x1

.field private static final ERROR_FAIL:I = 0x2

.field private static final ERROR_OK:I = 0x0

.field private static final TAG:Ljava/lang/String; = "NvAndroidVideoFileReader"

.field private static m_setOnFrameAvailableListener2:Ljava/lang/reflect/Method; = null

.field private static final m_verbose:Z = false


# instance fields
.field private m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

.field private m_decoder:Landroid/media/MediaCodec;

.field m_decoderInputBuffers:[Ljava/nio/ByteBuffer;

.field private m_decoderSetupFailed:Z

.field private m_decoderStarted:Z

.field private m_duration:J

.field private m_extractor:Landroid/media/MediaExtractor;

.field private m_extractorInOriginalState:Z

.field private m_firstPlaybackTexFrameUnconsumed:Z

.field private m_format:Landroid/media/MediaFormat;

.field private m_frameAvailable:Z

.field private m_frameSyncObject:Ljava/lang/Object;

.field private m_handler:Landroid/os/Handler;

.field private m_inputBufferQueued:Z

.field private m_lastSeekActualTimestamp:J

.field private m_lastSeekTimestamp:J

.field private m_pendingInputFrameCount:I

.field private m_sawInputEOS:Z

.field private m_sawOutputEOS:Z

.field private m_surface:Landroid/view/Surface;

.field private m_surfaceTexture:Landroid/graphics/SurfaceTexture;

.field private m_surfaceTextureCreationSemaphore:Ljava/util/concurrent/Semaphore;

.field private m_temporalLayerEndTime:J

.field private m_texId:I

.field private m_timestampOfCurTexFrame:J

.field private m_timestampOfLastDecodedFrame:J

.field private m_usedTemporalLayer:I

.field private m_videoTrackIndex:I


# direct methods
.method static constructor <clinit>()V
    .locals 5

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x15

    if-lt v0, v1, :cond_0

    :try_start_0
    const-class v0, Landroid/graphics/SurfaceTexture;

    const-string v1, "setOnFrameAvailableListener"

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Class;

    const/4 v3, 0x0

    const-class v4, Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;

    aput-object v4, v2, v3

    const/4 v3, 0x1

    const-class v4, Landroid/os/Handler;

    aput-object v4, v2, v3

    invoke-virtual {v0, v1, v2}, Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    sput-object v0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_setOnFrameAvailableListener2:Ljava/lang/reflect/Method;

    const-string v0, "NvAndroidVideoFileReader"

    const-string v1, "New SurfaceTexture.setOnFrameAvailableListener() method is available!"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const/4 v0, 0x0

    sput-object v0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_setOnFrameAvailableListener2:Ljava/lang/reflect/Method;

    :cond_0
    :goto_0
    return-void
.end method

.method constructor <init>(Landroid/os/Handler;)V
    .locals 5

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_handler:Landroid/os/Handler;

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    const/4 v1, -0x1

    iput v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    const-wide/16 v2, 0x0

    iput-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    const/4 v2, 0x1

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surface:Landroid/view/Surface;

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    const/4 v2, 0x0

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderSetupFailed:Z

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderStarted:Z

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderInputBuffers:[Ljava/nio/ByteBuffer;

    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    const-wide/high16 v3, -0x8000000000000000L

    iput-wide v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    iput-wide v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z

    iput v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    iput-wide v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekTimestamp:J

    iput-wide v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekActualTimestamp:J

    iput v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    const-wide/16 v0, -0x1

    iput-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_temporalLayerEndTime:J

    iput-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_handler:Landroid/os/Handler;

    new-instance p1, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {p1}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    iput-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    return-void
.end method

.method private AwaitNewImage()Z
    .locals 6

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    monitor-enter v0

    :cond_0
    :try_start_0
    iget-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v2, 0x0

    if-nez v1, :cond_1

    :try_start_1
    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    const-wide/16 v3, 0xbb8

    invoke-virtual {v1, v3, v4}, Ljava/lang/Object;->wait(J)V

    iget-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    if-nez v1, :cond_0

    const-string v1, "NvAndroidVideoFileReader"

    const-string v3, "Frame wait timed out!"

    invoke-static {v1, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :try_start_2
    monitor-exit v0

    return v2

    :catch_0
    move-exception v1

    const-string v3, "NvAndroidVideoFileReader"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, ""

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/InterruptedException;->getMessage()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {v1}, Ljava/lang/InterruptedException;->printStackTrace()V

    monitor-exit v0

    return v2

    :cond_1
    iput-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    invoke-virtual {v0}, Landroid/graphics/SurfaceTexture;->updateTexImage()V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1

    const/4 v0, 0x1

    return v0

    :catch_1
    move-exception v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, ""

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v3, "NvAndroidVideoFileReader"

    invoke-static {v3, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    return v2

    :catchall_0
    move-exception v1

    :try_start_4
    monitor-exit v0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    throw v1
.end method

.method private CleanupDecoder()V
    .locals 5

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderStarted:Z

    const/4 v2, 0x0

    if-eqz v0, :cond_2

    :try_start_0
    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->DrainOutputBuffers()V

    :cond_0
    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    if-eqz v0, :cond_1

    :try_start_1
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->flush()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    :catch_0
    :try_start_2
    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z

    :cond_1
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->stop()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    goto :goto_0

    :catch_1
    move-exception v0

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, ""

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v4, "NvAndroidVideoFileReader"

    invoke-static {v4, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    :goto_0
    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderStarted:Z

    iput-object v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderInputBuffers:[Ljava/nio/ByteBuffer;

    :cond_2
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->release()V

    iput-object v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    :cond_3
    const-wide/high16 v2, -0x8000000000000000L

    iput-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    iput-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    iput v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    return-void
.end method

.method private DecodeToFrame(JJ)I
    .locals 0

    :try_start_0
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/cdv/io/NvAndroidVideoFileReader;->DoDecodeToFrame(JJ)I

    move-result p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p1

    :catch_0
    move-exception p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, ""

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    const-string p3, "NvAndroidVideoFileReader"

    invoke-static {p3, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CleanupDecoder()V

    const/4 p1, 0x2

    return p1
.end method

.method private DoDecodeToFrame(JJ)I
    .locals 20

    move-object/from16 v1, p0

    iget-object v0, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderInputBuffers:[Ljava/nio/ByteBuffer;

    array-length v0, v0

    div-int/lit8 v0, v0, 0x3

    const/4 v2, 0x2

    invoke-static {v0, v2}, Ljava/lang/Math;->max(II)I

    move-result v0

    const/4 v3, 0x0

    move v4, v3

    :cond_0
    iget-boolean v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    const-wide/32 v6, 0x186a0

    const-wide/high16 v8, -0x8000000000000000L

    const/4 v10, 0x1

    if-nez v5, :cond_14

    iget-boolean v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    if-nez v5, :cond_5

    iget-object v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    const-wide/16 v11, 0xfa0

    invoke-virtual {v5, v11, v12}, Landroid/media/MediaCodec;->dequeueInputBuffer(J)I

    move-result v14

    if-ltz v14, :cond_5

    iget-object v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderInputBuffers:[Ljava/nio/ByteBuffer;

    aget-object v5, v5, v14

    :goto_0
    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v11, v5, v3}, Landroid/media/MediaExtractor;->readSampleData(Ljava/nio/ByteBuffer;I)I

    move-result v16

    if-gez v16, :cond_1

    iget-object v13, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    const/4 v15, 0x0

    const/16 v16, 0x0

    const-wide/16 v17, 0x0

    const/16 v19, 0x4

    invoke-virtual/range {v13 .. v19}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    iput-boolean v10, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    goto :goto_2

    :cond_1
    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v11}, Landroid/media/MediaExtractor;->getSampleTrackIndex()I

    move-result v11

    iget v12, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    if-eq v11, v12, :cond_2

    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V

    const-string v12, "WEIRD: got sample from track "

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v12, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v12}, Landroid/media/MediaExtractor;->getSampleTrackIndex()I

    move-result v12

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v12, ", expected "

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v12, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v11

    const-string v12, "NvAndroidVideoFileReader"

    invoke-static {v12, v11}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :cond_2
    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v11}, Landroid/media/MediaExtractor;->getSampleTime()J

    move-result-wide v11

    iget-object v13, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v13}, Landroid/media/MediaExtractor;->getSampleFlags()I

    move-result v13

    and-int/2addr v13, v10

    if-eqz v13, :cond_3

    move v13, v10

    goto :goto_1

    :cond_3
    move v13, v3

    :goto_1
    if-nez v13, :cond_4

    invoke-direct {v1, v5, v11, v12}, Lcom/cdv/io/NvAndroidVideoFileReader;->canSkipFrame(Ljava/nio/ByteBuffer;J)Z

    move-result v13

    if-eqz v13, :cond_4

    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v11}, Landroid/media/MediaExtractor;->advance()Z

    iput-boolean v3, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z

    goto :goto_0

    :cond_4
    iget-object v13, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    const/4 v15, 0x0

    const/16 v19, 0x0

    move-wide/from16 v17, v11

    invoke-virtual/range {v13 .. v19}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    iput-boolean v10, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z

    iget v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    add-int/2addr v5, v10

    iput v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    iget-object v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v5}, Landroid/media/MediaExtractor;->advance()Z

    iput-boolean v3, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z

    :cond_5
    :goto_2
    iget v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    if-gt v5, v0, :cond_7

    iget-boolean v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    if-eqz v5, :cond_6

    goto :goto_3

    :cond_6
    move v5, v3

    goto :goto_4

    :cond_7
    :goto_3
    const/16 v5, 0xfa0

    :goto_4
    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    iget-object v12, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    int-to-long v13, v5

    invoke-virtual {v11, v12, v13, v14}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v5

    add-int/2addr v4, v10

    const/4 v11, -0x1

    if-ne v5, v11, :cond_8

    goto/16 :goto_8

    :cond_8
    const/4 v11, -0x3

    if-ne v5, v11, :cond_9

    goto/16 :goto_8

    :cond_9
    const/4 v11, -0x2

    if-ne v5, v11, :cond_a

    iget-object v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v5}, Landroid/media/MediaCodec;->getOutputFormat()Landroid/media/MediaFormat;

    goto/16 :goto_8

    :cond_a
    if-gez v5, :cond_b

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Unexpected result from decoder.dequeueOutputBuffer: "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v3, "NvAndroidVideoFileReader"

    invoke-static {v3, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v2

    :cond_b
    iget-object v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    iget v11, v11, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 v11, v11, 0x4

    if-eqz v11, :cond_c

    iput-boolean v10, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    :cond_c
    iget-boolean v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    if-nez v11, :cond_10

    iget-object v4, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v11, v4, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iput-wide v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    iget v4, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    sub-int/2addr v4, v10

    iput v4, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    cmp-long v4, p1, v8

    if-eqz v4, :cond_f

    iget-wide v8, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    sub-long v11, p1, p3

    cmp-long v4, v8, v11

    if-ltz v4, :cond_d

    move v4, v10

    goto :goto_5

    :cond_d
    move v4, v3

    :goto_5
    if-nez v4, :cond_e

    iget-wide v8, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    iget-wide v11, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    sub-long/2addr v11, v6

    cmp-long v6, v8, v11

    if-ltz v6, :cond_e

    move v4, v3

    move v6, v10

    goto :goto_6

    :cond_e
    move v6, v3

    move v10, v4

    move v4, v6

    goto :goto_6

    :cond_f
    move v4, v3

    move v6, v4

    goto :goto_6

    :cond_10
    move v6, v3

    move v10, v6

    :goto_6
    if-eqz v10, :cond_11

    iget-object v7, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    monitor-enter v7

    :try_start_0
    iput-boolean v3, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    monitor-exit v7

    goto :goto_7

    :catchall_0
    move-exception v0

    monitor-exit v7
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_11
    :goto_7
    iget-object v7, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v7, v5, v10}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    if-eqz v10, :cond_13

    invoke-direct/range {p0 .. p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->AwaitNewImage()Z

    move-result v5

    if-eqz v5, :cond_12

    iget-object v5, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v7, v5, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iput-wide v7, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    if-nez v6, :cond_13

    return v3

    :cond_12
    const-string v0, "NvAndroidVideoFileReader"

    const-string v3, "Render decoded frame to surface texture failed!"

    invoke-static {v0, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v2

    :cond_13
    :goto_8
    const/16 v5, 0x64

    if-le v4, v5, :cond_0

    const-string v0, "NvAndroidVideoFileReader"

    const-string v3, "We have tried too many times and can\'t decode a frame!"

    invoke-static {v0, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v2

    :cond_14
    cmp-long v0, p1, v8

    if-eqz v0, :cond_15

    iget-wide v4, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    cmp-long v0, v4, v8

    if-eqz v0, :cond_15

    iget-wide v8, v1, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    sub-long/2addr v8, v6

    cmp-long v0, v4, v8

    if-ltz v0, :cond_15

    return v3

    :cond_15
    return v10
.end method

.method private DrainOutputBuffers()V
    .locals 6

    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    if-eqz v0, :cond_7

    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    if-eqz v0, :cond_0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    move v1, v0

    :cond_1
    iget-boolean v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    if-nez v2, :cond_7

    iget-object v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    iget-object v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    const-wide/16 v4, 0x1388

    invoke-virtual {v2, v3, v4, v5}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v2

    const/4 v3, 0x1

    add-int/2addr v1, v3

    const/4 v4, -0x1

    const-string v5, "NvAndroidVideoFileReader"

    if-ne v2, v4, :cond_2

    goto :goto_0

    :cond_2
    const/4 v4, -0x3

    if-ne v2, v4, :cond_3

    goto :goto_0

    :cond_3
    const/4 v4, -0x2

    if-ne v2, v4, :cond_4

    goto :goto_0

    :cond_4
    if-gez v2, :cond_5

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "DrainDecoderBuffers(): Unexpected result from decoder.dequeueOutputBuffer: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_5
    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_bufferInfo:Landroid/media/MediaCodec$BufferInfo;

    iget v1, v1, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 v1, v1, 0x4

    if-eqz v1, :cond_6

    iput-boolean v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    :cond_6
    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v1, v2, v0}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    move v1, v0

    :goto_0
    const/16 v2, 0x64

    if-le v1, v2, :cond_1

    const-string v0, "DrainDecoderBuffers(): We have tried too many times and can\'t decode a frame!"

    invoke-static {v5, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_7
    :goto_1
    return-void
.end method

.method private InvalidLastSeekTimestamp()V
    .locals 2

    const-wide/high16 v0, -0x8000000000000000L

    iput-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekTimestamp:J

    iput-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekActualTimestamp:J

    return-void
.end method

.method private IsValid()Z
    .locals 1

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private SeekInternal(JJ)I
    .locals 7

    iget-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long v2, v0, v2

    const/4 v3, 0x1

    const-wide/32 v4, 0x16e360

    const/4 v6, 0x0

    if-eqz v2, :cond_0

    cmp-long v2, p1, v0

    if-lez v2, :cond_0

    add-long/2addr v0, v4

    cmp-long v0, p1, v0

    if-gez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z

    if-eqz v0, :cond_1

    cmp-long v0, p1, v4

    if-gez v0, :cond_1

    goto :goto_0

    :cond_1
    move v3, v6

    :goto_0
    if-nez v3, :cond_4

    const/4 v0, 0x2

    :try_start_0
    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v1, p1, p2, v6}, Landroid/media/MediaExtractor;->seekTo(JI)V

    iget-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawInputEOS:Z

    if-nez v1, :cond_3

    iget-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_sawOutputEOS:Z

    if-eqz v1, :cond_2

    goto :goto_1

    :cond_2
    iget-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    if-eqz v1, :cond_4

    :try_start_1
    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v1}, Landroid/media/MediaCodec;->flush()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    :catch_0
    :try_start_2
    iput-boolean v6, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_inputBufferQueued:Z

    iput v6, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_pendingInputFrameCount:I

    goto :goto_2

    :cond_3
    :goto_1
    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CleanupDecoder()V

    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    const-string v2, "mime"

    invoke-virtual {v1, v2}, Landroid/media/MediaFormat;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v1}, Lcom/cdv/io/NvAndroidVideoFileReader;->SetupDecoder(Ljava/lang/String;)Z

    move-result v1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1

    if-nez v1, :cond_4

    return v0

    :catch_1
    move-exception p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, ""

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    const-string p3, "NvAndroidVideoFileReader"

    invoke-static {p3, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    return v0

    :cond_4
    :goto_2
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/cdv/io/NvAndroidVideoFileReader;->DecodeToFrame(JJ)I

    move-result p1

    return p1
.end method

.method private SetupDecoder(Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x0

    :try_start_0
    invoke-static {p1}, Landroid/media/MediaCodec;->createDecoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object p1

    iput-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    iget-object v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surface:Landroid/view/Surface;

    const/4 v3, 0x0

    invoke-virtual {p1, v1, v2, v3, v0}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {p1}, Landroid/media/MediaCodec;->start()V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderStarted:Z

    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoder:Landroid/media/MediaCodec;

    invoke-virtual {v1}, Landroid/media/MediaCodec;->getInputBuffers()[Ljava/nio/ByteBuffer;

    move-result-object v1

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderInputBuffers:[Ljava/nio/ByteBuffer;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return p1

    :catch_0
    move-exception p1

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, ""

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const-string v2, "NvAndroidVideoFileReader"

    invoke-static {v2, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CleanupDecoder()V

    return v0
.end method

.method static synthetic access$002(Lcom/cdv/io/NvAndroidVideoFileReader;Landroid/graphics/SurfaceTexture;)Landroid/graphics/SurfaceTexture;
    .locals 0

    iput-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    return-object p1
.end method

.method static synthetic access$100(Lcom/cdv/io/NvAndroidVideoFileReader;)I
    .locals 0

    iget p0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_texId:I

    return p0
.end method

.method static synthetic access$200(Lcom/cdv/io/NvAndroidVideoFileReader;)Ljava/util/concurrent/Semaphore;
    .locals 0

    iget-object p0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTextureCreationSemaphore:Ljava/util/concurrent/Semaphore;

    return-object p0
.end method

.method private canSkipFrame(Ljava/nio/ByteBuffer;J)Z
    .locals 3

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    :cond_0
    iget v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    if-gez v1, :cond_1

    return v0

    :cond_1
    iget-wide v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_temporalLayerEndTime:J

    cmp-long p2, p2, v1

    if-ltz p2, :cond_2

    return v0

    :cond_2
    const/16 p2, 0x10

    new-array p2, p2, [B

    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->position()I

    move-result p3

    invoke-virtual {p1, p2}, Ljava/nio/ByteBuffer;->get([B)Ljava/nio/ByteBuffer;

    invoke-virtual {p1, p3}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    const/4 p1, 0x4

    aget-byte p1, p2, p1

    and-int/lit8 p1, p1, 0x1f

    aget-byte p3, p2, v0

    const/4 v1, 0x1

    if-nez p3, :cond_5

    aget-byte p3, p2, v1

    if-nez p3, :cond_5

    const/4 p3, 0x2

    aget-byte p3, p2, p3

    if-nez p3, :cond_5

    const/4 p3, 0x3

    aget-byte p3, p2, p3

    if-ne p3, v1, :cond_5

    const/16 p3, 0xe

    if-eq p1, p3, :cond_3

    const/16 p3, 0x14

    if-ne p1, p3, :cond_5

    :cond_3
    const/4 p1, 0x5

    aget-byte p3, p2, p1

    and-int/lit16 p3, p3, 0xff

    const/4 v2, 0x7

    shr-int/2addr p3, v2

    if-lez p3, :cond_4

    move p3, v1

    goto :goto_0

    :cond_4
    move p3, v0

    :goto_0
    if-eqz p3, :cond_5

    aget-byte p2, p2, v2

    and-int/lit16 p2, p2, 0xff

    shr-int/lit8 p1, p2, 0x5

    and-int/2addr p1, v2

    iget p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    if-le p1, p2, :cond_5

    move v0, v1

    :cond_5
    return v0
.end method


# virtual methods
.method public CloseFile()V
    .locals 3

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->InvalidLastSeekTimestamp()V

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CleanupDecoder()V

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surface:Landroid/view/Surface;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/Surface;->release()V

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surface:Landroid/view/Surface;

    :cond_0
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Landroid/graphics/SurfaceTexture;->release()V

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    :cond_1
    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    const/4 v2, -0x1

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Landroid/media/MediaExtractor;->release()V

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    iput v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z

    :cond_2
    iput v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    const-wide/16 v0, -0x1

    iput-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_temporalLayerEndTime:J

    return-void
.end method

.method public GetNextVideoFrameForPlayback()I
    .locals 6

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->IsValid()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    const/4 v1, 0x0

    if-nez v0, :cond_1

    const-wide/high16 v2, -0x8000000000000000L

    const-wide/16 v4, 0x0

    invoke-direct {p0, v2, v3, v4, v5}, Lcom/cdv/io/NvAndroidVideoFileReader;->DecodeToFrame(JJ)I

    move-result v0

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->InvalidLastSeekTimestamp()V

    if-eqz v0, :cond_2

    return v0

    :cond_1
    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    :cond_2
    return v1
.end method

.method public GetTimestampOfCurrentTextureFrame()J
    .locals 2

    iget-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    return-wide v0
.end method

.method public GetTransformMatrixOfSurfaceTexture([F)V
    .locals 1

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Landroid/graphics/SurfaceTexture;->getTransformMatrix([F)V

    :cond_0
    return-void
.end method

.method public OpenFile(Ljava/lang/String;ILandroid/content/res/AssetManager;I)Z
    .locals 10

    const-string v0, ""

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->IsValid()Z

    move-result v1

    const-string v2, "NvAndroidVideoFileReader"

    const/4 v3, 0x0

    if-eqz v1, :cond_0

    const-string p1, "You can\'t call OpenFile() twice!"

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return v3

    :cond_0
    :try_start_0
    new-instance v1, Landroid/media/MediaExtractor;

    invoke-direct {v1}, Landroid/media/MediaExtractor;-><init>()V

    iput-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    if-nez p3, :cond_1

    iget-object p3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {p3, p1}, Landroid/media/MediaExtractor;->setDataSource(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    invoke-virtual {p3, p1}, Landroid/content/res/AssetManager;->openFd(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;

    move-result-object p3

    iget-object v4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {p3}, Landroid/content/res/AssetFileDescriptor;->getFileDescriptor()Ljava/io/FileDescriptor;

    move-result-object v5

    invoke-virtual {p3}, Landroid/content/res/AssetFileDescriptor;->getStartOffset()J

    move-result-wide v6

    invoke-virtual {p3}, Landroid/content/res/AssetFileDescriptor;->getLength()J

    move-result-wide v8

    invoke-virtual/range {v4 .. v9}, Landroid/media/MediaExtractor;->setDataSource(Ljava/io/FileDescriptor;JJ)V

    invoke-virtual {p3}, Landroid/content/res/AssetFileDescriptor;->close()V

    :goto_0
    const/4 p3, 0x1

    iput-boolean p3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractorInOriginalState:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    iget-object v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v1}, Landroid/media/MediaExtractor;->getTrackCount()I

    move-result v1

    move v4, v3

    :goto_1
    const-string v5, "mime"

    if-ge v4, v1, :cond_3

    iget-object v6, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {v6, v4}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object v6

    invoke-virtual {v6, v5}, Landroid/media/MediaFormat;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    const-string v7, "video/"

    invoke-virtual {v6, v7}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_2

    iput v4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    goto :goto_2

    :cond_2
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    :cond_3
    :goto_2
    iget v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    if-gez v1, :cond_4

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "Failed to find a video track from "

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CloseFile()V

    return v3

    :cond_4
    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    invoke-virtual {p1, v1}, Landroid/media/MediaExtractor;->selectTrack(I)V

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_extractor:Landroid/media/MediaExtractor;

    iget v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_videoTrackIndex:I

    invoke-virtual {p1, v1}, Landroid/media/MediaExtractor;->getTrackFormat(I)Landroid/media/MediaFormat;

    move-result-object p1

    iput-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x10

    if-ne p1, v1, :cond_5

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    const-string v1, "max-input-size"

    invoke-virtual {p1, v1, v3}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    :cond_5
    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt p1, v1, :cond_7

    if-ltz p4, :cond_7

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    if-lez p4, :cond_6

    goto :goto_3

    :cond_6
    const/16 p4, 0x78

    :goto_3
    const-string v1, "operating-rate"

    invoke-virtual {p1, v1, p4}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    :cond_7
    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    const-string p4, "durationUs"

    invoke-virtual {p1, p4}, Landroid/media/MediaFormat;->getLong(Ljava/lang/String;)J

    move-result-wide v6

    iput-wide v6, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_format:Landroid/media/MediaFormat;

    invoke-virtual {p1, v5}, Landroid/media/MediaFormat;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    :try_start_1
    sget-object p4, Lcom/cdv/io/NvAndroidVideoFileReader;->m_setOnFrameAvailableListener2:Ljava/lang/reflect/Method;

    if-eqz p4, :cond_8

    new-instance p4, Landroid/graphics/SurfaceTexture;

    invoke-direct {p4, p2}, Landroid/graphics/SurfaceTexture;-><init>(I)V

    iput-object p4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    sget-object p2, Lcom/cdv/io/NvAndroidVideoFileReader;->m_setOnFrameAvailableListener2:Ljava/lang/reflect/Method;

    iget-object p4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/Object;

    aput-object p0, v1, v3

    iget-object v4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_handler:Landroid/os/Handler;

    aput-object v4, v1, p3

    invoke-virtual {p2, p4, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_4

    :cond_8
    new-instance p4, Ljava/util/concurrent/Semaphore;

    invoke-direct {p4, v3}, Ljava/util/concurrent/Semaphore;-><init>(I)V

    iput-object p4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTextureCreationSemaphore:Ljava/util/concurrent/Semaphore;

    iput p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_texId:I

    iget-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_handler:Landroid/os/Handler;

    new-instance p4, Lcom/cdv/io/NvAndroidVideoFileReader$1;

    invoke-direct {p4, p0}, Lcom/cdv/io/NvAndroidVideoFileReader$1;-><init>(Lcom/cdv/io/NvAndroidVideoFileReader;)V

    invoke-virtual {p2, p4}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    iget-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTextureCreationSemaphore:Ljava/util/concurrent/Semaphore;

    invoke-virtual {p2}, Ljava/util/concurrent/Semaphore;->acquire()V

    const/4 p2, 0x0

    iput-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTextureCreationSemaphore:Ljava/util/concurrent/Semaphore;

    iput v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_texId:I

    iget-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    if-nez p2, :cond_9

    invoke-virtual {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CloseFile()V

    return v3

    :cond_9
    iget-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    invoke-virtual {p2, p0}, Landroid/graphics/SurfaceTexture;->setOnFrameAvailableListener(Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;)V

    :goto_4
    new-instance p2, Landroid/view/Surface;

    iget-object p4, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surfaceTexture:Landroid/graphics/SurfaceTexture;

    invoke-direct {p2, p4}, Landroid/view/Surface;-><init>(Landroid/graphics/SurfaceTexture;)V

    iput-object p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_surface:Landroid/view/Surface;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    iput-boolean v3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderSetupFailed:Z

    invoke-direct {p0, p1}, Lcom/cdv/io/NvAndroidVideoFileReader;->SetupDecoder(Ljava/lang/String;)Z

    move-result p1

    if-nez p1, :cond_a

    iput-boolean p3, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderSetupFailed:Z

    invoke-virtual {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CloseFile()V

    return v3

    :cond_a
    const/4 p1, -0x1

    iput p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    const-wide/16 p1, -0x1

    iput-wide p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_temporalLayerEndTime:J

    return p3

    :catch_0
    move-exception p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v2, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    invoke-virtual {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CloseFile()V

    return v3

    :catch_1
    move-exception p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v2, p2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {p1}, Ljava/lang/Exception;->printStackTrace()V

    invoke-virtual {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->CloseFile()V

    return v3
.end method

.method public SeekVideoFrame(JJ)I
    .locals 6

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->IsValid()Z

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_0

    return v1

    :cond_0
    const-wide/16 v2, 0x0

    invoke-static {p1, p2, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iget-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    cmp-long v0, p1, v2

    if-ltz v0, :cond_2

    const-wide/16 v4, 0x61a8

    add-long/2addr v4, v2

    cmp-long p1, p1, v4

    if-gez p1, :cond_1

    const-wide/16 p1, 0x1

    sub-long p1, v2, p1

    goto :goto_0

    :cond_1
    return v1

    :cond_2
    :goto_0
    iget-wide v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long v2, v0, v2

    if-eqz v2, :cond_3

    sub-long v0, p1, v0

    invoke-static {v0, v1}, Ljava/lang/Math;->abs(J)J

    move-result-wide v0

    cmp-long v0, v0, p3

    if-gtz v0, :cond_3

    const/4 p1, 0x0

    return p1

    :cond_3
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/cdv/io/NvAndroidVideoFileReader;->SeekInternal(JJ)I

    move-result p3

    if-nez p3, :cond_4

    iput-wide p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekTimestamp:J

    iget-wide p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    iput-wide p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekActualTimestamp:J

    goto :goto_1

    :cond_4
    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->InvalidLastSeekTimestamp()V

    :goto_1
    return p3
.end method

.method public SetDecodeTemporalLayer(IJ)V
    .locals 1

    iget v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    if-ne p1, v0, :cond_0

    return-void

    :cond_0
    iput-wide p2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_temporalLayerEndTime:J

    iput p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_usedTemporalLayer:I

    return-void
.end method

.method public StartPlayback(JJ)I
    .locals 7

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->IsValid()Z

    move-result v0

    const/4 v1, 0x1

    if-nez v0, :cond_0

    return v1

    :cond_0
    const-wide/16 v2, 0x0

    invoke-static {p1, p2, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iget-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_duration:J

    cmp-long v0, p1, v2

    if-ltz v0, :cond_1

    return v1

    :cond_1
    iget-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekTimestamp:J

    const-wide/high16 v4, -0x8000000000000000L

    cmp-long v0, v2, v4

    if-eqz v0, :cond_2

    cmp-long v0, p1, v2

    if-nez v0, :cond_2

    iget-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_lastSeekActualTimestamp:J

    cmp-long v0, v2, v4

    if-eqz v0, :cond_2

    move-wide p1, v2

    :cond_2
    iget-wide v2, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfCurTexFrame:J

    cmp-long v0, p1, v2

    const/4 v4, 0x0

    if-nez v0, :cond_3

    iget-wide v5, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_timestampOfLastDecodedFrame:J

    cmp-long v0, v2, v5

    if-nez v0, :cond_3

    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    return v4

    :cond_3
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/cdv/io/NvAndroidVideoFileReader;->SeekInternal(JJ)I

    move-result p1

    invoke-direct {p0}, Lcom/cdv/io/NvAndroidVideoFileReader;->InvalidLastSeekTimestamp()V

    if-eqz p1, :cond_4

    return p1

    :cond_4
    iput-boolean v1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_firstPlaybackTexFrameUnconsumed:Z

    return v4
.end method

.method public hasDecoderSetupFailed()Z
    .locals 1

    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_decoderSetupFailed:Z

    return v0
.end method

.method public onFrameAvailable(Landroid/graphics/SurfaceTexture;)V
    .locals 2

    iget-object p1, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    monitor-enter p1

    :try_start_0
    iget-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    if-eqz v0, :cond_0

    const-string v0, "NvAndroidVideoFileReader"

    const-string v1, "m_frameAvailable already set, frame could be dropped!"

    invoke-static {v0, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_0
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameAvailable:Z

    iget-object v0, p0, Lcom/cdv/io/NvAndroidVideoFileReader;->m_frameSyncObject:Ljava/lang/Object;

    invoke-virtual {v0}, Ljava/lang/Object;->notifyAll()V

    monitor-exit p1

    return-void

    :catchall_0
    move-exception v0

    monitor-exit p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method
