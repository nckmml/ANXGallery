package com.cdv.io;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.Semaphore;

public class NvAndroidVideoFileReader implements SurfaceTexture.OnFrameAvailableListener {
    private static final int ERROR_EOF = 1;
    private static final int ERROR_FAIL = 2;
    private static final int ERROR_OK = 0;
    private static final String TAG = "NvAndroidVideoFileReader";
    private static Method m_setOnFrameAvailableListener2;
    private static final boolean m_verbose = false;
    private MediaCodec.BufferInfo m_bufferInfo = null;
    private MediaCodec m_decoder = null;
    ByteBuffer[] m_decoderInputBuffers = null;
    private boolean m_decoderSetupFailed = false;
    private boolean m_decoderStarted = false;
    private long m_duration = 0;
    private MediaExtractor m_extractor = null;
    private boolean m_extractorInOriginalState = true;
    private boolean m_firstPlaybackTexFrameUnconsumed = false;
    private MediaFormat m_format = null;
    private boolean m_frameAvailable = false;
    private Object m_frameSyncObject = new Object();
    private Handler m_handler = null;
    private boolean m_inputBufferQueued = false;
    private long m_lastSeekActualTimestamp = Long.MIN_VALUE;
    private long m_lastSeekTimestamp = Long.MIN_VALUE;
    private int m_pendingInputFrameCount = 0;
    private boolean m_sawInputEOS = false;
    private boolean m_sawOutputEOS = false;
    private Surface m_surface = null;
    /* access modifiers changed from: private */
    public SurfaceTexture m_surfaceTexture = null;
    /* access modifiers changed from: private */
    public Semaphore m_surfaceTextureCreationSemaphore;
    private long m_temporalLayerEndTime = -1;
    /* access modifiers changed from: private */
    public int m_texId;
    private long m_timestampOfCurTexFrame = Long.MIN_VALUE;
    private long m_timestampOfLastDecodedFrame = Long.MIN_VALUE;
    private int m_usedTemporalLayer = -1;
    private int m_videoTrackIndex = -1;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                m_setOnFrameAvailableListener2 = SurfaceTexture.class.getDeclaredMethod("setOnFrameAvailableListener", new Class[]{SurfaceTexture.OnFrameAvailableListener.class, Handler.class});
                Log.d(TAG, "New SurfaceTexture.setOnFrameAvailableListener() method is available!");
            } catch (Exception unused) {
                m_setOnFrameAvailableListener2 = null;
            }
        }
    }

    NvAndroidVideoFileReader(Handler handler) {
        this.m_handler = handler;
        this.m_bufferInfo = new MediaCodec.BufferInfo();
    }

    private boolean AwaitNewImage() {
        synchronized (this.m_frameSyncObject) {
            do {
                if (!this.m_frameAvailable) {
                    try {
                        this.m_frameSyncObject.wait(3000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "" + e.getMessage());
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    this.m_frameAvailable = false;
                    try {
                        this.m_surfaceTexture.updateTexImage();
                        return true;
                    } catch (Exception e2) {
                        Log.e(TAG, "" + e2.getMessage());
                        e2.printStackTrace();
                        return false;
                    }
                }
            } while (this.m_frameAvailable);
            Log.e(TAG, "Frame wait timed out!");
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:12|13|14|15) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001e */
    private void CleanupDecoder() {
        if (this.m_decoder != null) {
            if (this.m_decoderStarted) {
                try {
                    if (this.m_sawInputEOS && !this.m_sawOutputEOS) {
                        DrainOutputBuffers();
                    }
                    if (this.m_inputBufferQueued) {
                        this.m_decoder.flush();
                        this.m_inputBufferQueued = false;
                    }
                    this.m_decoder.stop();
                } catch (Exception e) {
                    Log.e(TAG, "" + e.getMessage());
                    e.printStackTrace();
                }
                this.m_decoderStarted = false;
                this.m_decoderInputBuffers = null;
            }
            this.m_decoder.release();
            this.m_decoder = null;
        }
        this.m_timestampOfLastDecodedFrame = Long.MIN_VALUE;
        this.m_timestampOfCurTexFrame = Long.MIN_VALUE;
        this.m_firstPlaybackTexFrameUnconsumed = false;
        this.m_pendingInputFrameCount = 0;
        this.m_sawInputEOS = false;
        this.m_sawOutputEOS = false;
    }

    private int DecodeToFrame(long j, long j2) {
        try {
            return DoDecodeToFrame(j, j2);
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
            CleanupDecoder();
            return 2;
        }
    }

    private int DoDecodeToFrame(long j, long j2) {
        boolean z;
        int dequeueInputBuffer;
        int max = Math.max(this.m_decoderInputBuffers.length / 3, 2);
        int i = 0;
        do {
            boolean z2 = true;
            if (!this.m_sawOutputEOS) {
                if (!this.m_sawInputEOS && (dequeueInputBuffer = this.m_decoder.dequeueInputBuffer(4000)) >= 0) {
                    ByteBuffer byteBuffer = this.m_decoderInputBuffers[dequeueInputBuffer];
                    while (true) {
                        int readSampleData = this.m_extractor.readSampleData(byteBuffer, 0);
                        if (readSampleData < 0) {
                            this.m_decoder.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                            this.m_sawInputEOS = true;
                            break;
                        }
                        if (this.m_extractor.getSampleTrackIndex() != this.m_videoTrackIndex) {
                            Log.w(TAG, "WEIRD: got sample from track " + this.m_extractor.getSampleTrackIndex() + ", expected " + this.m_videoTrackIndex);
                        }
                        long sampleTime = this.m_extractor.getSampleTime();
                        if (((this.m_extractor.getSampleFlags() & 1) != 0) || !canSkipFrame(byteBuffer, sampleTime)) {
                            this.m_decoder.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, 0);
                            this.m_inputBufferQueued = true;
                            this.m_pendingInputFrameCount++;
                            this.m_extractor.advance();
                            this.m_extractorInOriginalState = false;
                        } else {
                            this.m_extractor.advance();
                            this.m_extractorInOriginalState = false;
                        }
                    }
                }
                int dequeueOutputBuffer = this.m_decoder.dequeueOutputBuffer(this.m_bufferInfo, (long) ((this.m_pendingInputFrameCount > max || this.m_sawInputEOS) ? 4000 : 0));
                i++;
                if (!(dequeueOutputBuffer == -1 || dequeueOutputBuffer == -3)) {
                    if (dequeueOutputBuffer == -2) {
                        this.m_decoder.getOutputFormat();
                    } else if (dequeueOutputBuffer < 0) {
                        Log.e(TAG, "Unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                        return 2;
                    } else {
                        if ((this.m_bufferInfo.flags & 4) != 0) {
                            this.m_sawOutputEOS = true;
                        }
                        if (!this.m_sawOutputEOS) {
                            this.m_timestampOfLastDecodedFrame = this.m_bufferInfo.presentationTimeUs;
                            this.m_pendingInputFrameCount--;
                            if (j != Long.MIN_VALUE) {
                                boolean z3 = this.m_timestampOfLastDecodedFrame >= j - j2;
                                if (z3 || this.m_timestampOfLastDecodedFrame < this.m_duration - 100000) {
                                    z = false;
                                    z2 = z3;
                                    i = 0;
                                } else {
                                    i = 0;
                                    z = true;
                                }
                            } else {
                                i = 0;
                                z = false;
                            }
                        } else {
                            z = false;
                            z2 = false;
                        }
                        if (z2) {
                            synchronized (this.m_frameSyncObject) {
                                this.m_frameAvailable = false;
                            }
                        }
                        this.m_decoder.releaseOutputBuffer(dequeueOutputBuffer, z2);
                        if (z2) {
                            if (AwaitNewImage()) {
                                this.m_timestampOfCurTexFrame = this.m_bufferInfo.presentationTimeUs;
                                if (!z) {
                                    return 0;
                                }
                            } else {
                                Log.e(TAG, "Render decoded frame to surface texture failed!");
                                return 2;
                            }
                        }
                    }
                }
            } else {
                if (j != Long.MIN_VALUE) {
                    long j3 = this.m_timestampOfCurTexFrame;
                    return (j3 == Long.MIN_VALUE || j3 < this.m_duration - 100000) ? 1 : 0;
                }
            }
        } while (i <= 100);
        Log.e(TAG, "We have tried too many times and can't decode a frame!");
        return 2;
    }

    private void DrainOutputBuffers() {
        if (this.m_sawInputEOS && !this.m_sawOutputEOS) {
            int i = 0;
            while (!this.m_sawOutputEOS) {
                int dequeueOutputBuffer = this.m_decoder.dequeueOutputBuffer(this.m_bufferInfo, 5000);
                i++;
                if (!(dequeueOutputBuffer == -1 || dequeueOutputBuffer == -3 || dequeueOutputBuffer == -2)) {
                    if (dequeueOutputBuffer < 0) {
                        Log.e(TAG, "DrainDecoderBuffers(): Unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                        return;
                    }
                    if ((this.m_bufferInfo.flags & 4) != 0) {
                        this.m_sawOutputEOS = true;
                    }
                    this.m_decoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    i = 0;
                }
                if (i > 100) {
                    Log.e(TAG, "DrainDecoderBuffers(): We have tried too many times and can't decode a frame!");
                    return;
                }
            }
        }
    }

    private void InvalidLastSeekTimestamp() {
        this.m_lastSeekTimestamp = Long.MIN_VALUE;
        this.m_lastSeekActualTimestamp = Long.MIN_VALUE;
    }

    private boolean IsValid() {
        return this.m_decoder != null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|23|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x003b */
    private int SeekInternal(long j, long j2) {
        long j3 = this.m_timestampOfLastDecodedFrame;
        boolean z = true;
        if ((j3 == Long.MIN_VALUE || j <= j3 || j >= j3 + 1500000) && (!this.m_extractorInOriginalState || j >= 1500000)) {
            z = false;
        }
        if (!z) {
            try {
                this.m_extractor.seekTo(j, 0);
                if (!this.m_sawInputEOS) {
                    if (!this.m_sawOutputEOS) {
                        if (this.m_inputBufferQueued) {
                            this.m_decoder.flush();
                            this.m_inputBufferQueued = false;
                            this.m_pendingInputFrameCount = 0;
                        }
                    }
                }
                CleanupDecoder();
                if (!SetupDecoder(this.m_format.getString("mime"))) {
                    return 2;
                }
            } catch (Exception e) {
                Log.e(TAG, "" + e.getMessage());
                e.printStackTrace();
                return 2;
            }
        }
        return DecodeToFrame(j, j2);
    }

    private boolean SetupDecoder(String str) {
        try {
            this.m_decoder = MediaCodec.createDecoderByType(str);
            this.m_decoder.configure(this.m_format, this.m_surface, (MediaCrypto) null, 0);
            this.m_decoder.start();
            this.m_decoderStarted = true;
            this.m_decoderInputBuffers = this.m_decoder.getInputBuffers();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
            CleanupDecoder();
            return false;
        }
    }

    private boolean canSkipFrame(ByteBuffer byteBuffer, long j) {
        if (byteBuffer == null || this.m_usedTemporalLayer < 0 || j >= this.m_temporalLayerEndTime) {
            return false;
        }
        byte[] bArr = new byte[16];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        byte b = bArr[4] & 31;
        if (bArr[0] != 0 || bArr[1] != 0 || bArr[2] != 0 || bArr[3] != 1) {
            return false;
        }
        if (b != 14 && b != 20) {
            return false;
        }
        return (((bArr[5] & 255) >> 7) > 0) && (((bArr[7] & 255) >> 5) & 7) > this.m_usedTemporalLayer;
    }

    public void CloseFile() {
        InvalidLastSeekTimestamp();
        CleanupDecoder();
        Surface surface = this.m_surface;
        if (surface != null) {
            surface.release();
            this.m_surface = null;
        }
        SurfaceTexture surfaceTexture = this.m_surfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.m_surfaceTexture = null;
        }
        MediaExtractor mediaExtractor = this.m_extractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.m_extractor = null;
            this.m_videoTrackIndex = -1;
            this.m_format = null;
            this.m_duration = 0;
            this.m_extractorInOriginalState = true;
        }
        this.m_usedTemporalLayer = -1;
        this.m_temporalLayerEndTime = -1;
    }

    public int GetNextVideoFrameForPlayback() {
        if (!IsValid()) {
            return 1;
        }
        if (!this.m_firstPlaybackTexFrameUnconsumed) {
            int DecodeToFrame = DecodeToFrame(Long.MIN_VALUE, 0);
            InvalidLastSeekTimestamp();
            if (DecodeToFrame != 0) {
                return DecodeToFrame;
            }
        } else {
            this.m_firstPlaybackTexFrameUnconsumed = false;
        }
        return 0;
    }

    public long GetTimestampOfCurrentTextureFrame() {
        return this.m_timestampOfCurTexFrame;
    }

    public void GetTransformMatrixOfSurfaceTexture(float[] fArr) {
        SurfaceTexture surfaceTexture = this.m_surfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.getTransformMatrix(fArr);
        }
    }

    public boolean OpenFile(String str, int i, AssetManager assetManager, int i2) {
        if (IsValid()) {
            Log.e(TAG, "You can't call OpenFile() twice!");
            return false;
        }
        try {
            this.m_extractor = new MediaExtractor();
            if (assetManager == null) {
                this.m_extractor.setDataSource(str);
            } else {
                AssetFileDescriptor openFd = assetManager.openFd(str);
                this.m_extractor.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                openFd.close();
            }
            this.m_extractorInOriginalState = true;
            int trackCount = this.m_extractor.getTrackCount();
            int i3 = 0;
            while (true) {
                if (i3 >= trackCount) {
                    break;
                } else if (this.m_extractor.getTrackFormat(i3).getString("mime").startsWith("video/")) {
                    this.m_videoTrackIndex = i3;
                    break;
                } else {
                    i3++;
                }
            }
            int i4 = this.m_videoTrackIndex;
            if (i4 < 0) {
                Log.e(TAG, "Failed to find a video track from " + str);
                CloseFile();
                return false;
            }
            this.m_extractor.selectTrack(i4);
            this.m_format = this.m_extractor.getTrackFormat(this.m_videoTrackIndex);
            if (Build.VERSION.SDK_INT == 16) {
                this.m_format.setInteger("max-input-size", 0);
            }
            if (Build.VERSION.SDK_INT >= 23 && i2 >= 0) {
                MediaFormat mediaFormat = this.m_format;
                if (i2 <= 0) {
                    i2 = 120;
                }
                mediaFormat.setInteger("operating-rate", i2);
            }
            this.m_duration = this.m_format.getLong("durationUs");
            String string = this.m_format.getString("mime");
            try {
                if (m_setOnFrameAvailableListener2 != null) {
                    this.m_surfaceTexture = new SurfaceTexture(i);
                    m_setOnFrameAvailableListener2.invoke(this.m_surfaceTexture, new Object[]{this, this.m_handler});
                } else {
                    this.m_surfaceTextureCreationSemaphore = new Semaphore(0);
                    this.m_texId = i;
                    this.m_handler.post(new Runnable() {
                        public void run() {
                            try {
                                SurfaceTexture unused = NvAndroidVideoFileReader.this.m_surfaceTexture = new SurfaceTexture(NvAndroidVideoFileReader.this.m_texId);
                                NvAndroidVideoFileReader.this.m_surfaceTextureCreationSemaphore.release();
                            } catch (Exception e) {
                                Log.e(NvAndroidVideoFileReader.TAG, "" + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });
                    this.m_surfaceTextureCreationSemaphore.acquire();
                    this.m_surfaceTextureCreationSemaphore = null;
                    this.m_texId = 0;
                    if (this.m_surfaceTexture == null) {
                        CloseFile();
                        return false;
                    }
                    this.m_surfaceTexture.setOnFrameAvailableListener(this);
                }
                this.m_surface = new Surface(this.m_surfaceTexture);
                this.m_decoderSetupFailed = false;
                if (!SetupDecoder(string)) {
                    this.m_decoderSetupFailed = true;
                    CloseFile();
                    return false;
                }
                this.m_usedTemporalLayer = -1;
                this.m_temporalLayerEndTime = -1;
                return true;
            } catch (Exception e) {
                Log.e(TAG, "" + e.getMessage());
                e.printStackTrace();
                CloseFile();
                return false;
            }
        } catch (Exception e2) {
            Log.e(TAG, "" + e2.getMessage());
            e2.printStackTrace();
            CloseFile();
            return false;
        }
    }

    public int SeekVideoFrame(long j, long j2) {
        if (!IsValid()) {
            return 1;
        }
        long max = Math.max(j, 0);
        long j3 = this.m_duration;
        if (max >= j3) {
            if (max >= 25000 + j3) {
                return 1;
            }
            max = j3 - 1;
        }
        long j4 = this.m_timestampOfCurTexFrame;
        if (j4 != Long.MIN_VALUE && Math.abs(max - j4) <= j2) {
            return 0;
        }
        int SeekInternal = SeekInternal(max, j2);
        if (SeekInternal == 0) {
            this.m_lastSeekTimestamp = max;
            this.m_lastSeekActualTimestamp = this.m_timestampOfCurTexFrame;
        } else {
            InvalidLastSeekTimestamp();
        }
        return SeekInternal;
    }

    public void SetDecodeTemporalLayer(int i, long j) {
        if (i != this.m_usedTemporalLayer) {
            this.m_temporalLayerEndTime = j;
            this.m_usedTemporalLayer = i;
        }
    }

    public int StartPlayback(long j, long j2) {
        if (!IsValid()) {
            return 1;
        }
        long max = Math.max(j, 0);
        if (max >= this.m_duration) {
            return 1;
        }
        long j3 = this.m_lastSeekTimestamp;
        if (j3 != Long.MIN_VALUE && max == j3) {
            long j4 = this.m_lastSeekActualTimestamp;
            if (j4 != Long.MIN_VALUE) {
                max = j4;
            }
        }
        long j5 = this.m_timestampOfCurTexFrame;
        if (max == j5 && j5 == this.m_timestampOfLastDecodedFrame) {
            this.m_firstPlaybackTexFrameUnconsumed = true;
            return 0;
        }
        int SeekInternal = SeekInternal(max, j2);
        InvalidLastSeekTimestamp();
        if (SeekInternal != 0) {
            return SeekInternal;
        }
        this.m_firstPlaybackTexFrameUnconsumed = true;
        return 0;
    }

    public boolean hasDecoderSetupFailed() {
        return this.m_decoderSetupFailed;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this.m_frameSyncObject) {
            if (this.m_frameAvailable) {
                Log.e(TAG, "m_frameAvailable already set, frame could be dropped!");
            }
            this.m_frameAvailable = true;
            this.m_frameSyncObject.notifyAll();
        }
    }
}
