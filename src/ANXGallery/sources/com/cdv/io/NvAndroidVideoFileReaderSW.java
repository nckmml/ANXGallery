package com.cdv.io;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;
import android.view.Surface;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.nio.ByteBuffer;

public class NvAndroidVideoFileReaderSW {
    private static final int ERROR_EOF = 1;
    private static final int ERROR_FAIL = 2;
    private static final int ERROR_OK = 0;
    private static final String TAG = "NvAndroidVideoFileReaderSW";
    private static final boolean m_verbose = false;
    private MediaCodec.BufferInfo m_bufferInfo = null;
    private MediaCodec m_decoder = null;
    ByteBuffer[] m_decoderInputBuffers = null;
    ByteBuffer[] m_decoderOutputBuffers = null;
    private boolean m_decoderSetupFailed = false;
    private boolean m_decoderStarted = false;
    private long m_duration = 0;
    private MediaExtractor m_extractor = null;
    private boolean m_extractorInOriginalState = true;
    private MediaFormat m_format = null;
    private boolean m_inputBufferQueued = false;
    private long m_lastSeekActualTimestamp = Long.MIN_VALUE;
    private long m_lastSeekTimestamp = Long.MIN_VALUE;
    private long m_owner = 0;
    private int m_pendingInputFrameCount = 0;
    private boolean m_sawInputEOS = false;
    private boolean m_sawOutputEOS = false;
    private long m_timestampOfLastCopiedFrame = Long.MIN_VALUE;
    private long m_timestampOfLastDecodedFrame = Long.MIN_VALUE;
    private int m_videoTrackIndex = -1;

    NvAndroidVideoFileReaderSW(long j) {
        this.m_owner = j;
        this.m_bufferInfo = new MediaCodec.BufferInfo();
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
        this.m_timestampOfLastCopiedFrame = Long.MIN_VALUE;
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
        int i;
        boolean z;
        boolean z2;
        int dequeueInputBuffer;
        int max = Math.max(this.m_decoderInputBuffers.length / 3, 2);
        int i2 = 0;
        while (!this.m_sawOutputEOS) {
            if (!this.m_sawInputEOS && (dequeueInputBuffer = this.m_decoder.dequeueInputBuffer(4000)) >= 0) {
                int readSampleData = this.m_extractor.readSampleData(this.m_decoderInputBuffers[dequeueInputBuffer], 0);
                if (readSampleData < 0) {
                    this.m_decoder.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                    this.m_sawInputEOS = true;
                } else {
                    if (this.m_extractor.getSampleTrackIndex() != this.m_videoTrackIndex) {
                        Log.w(TAG, "WEIRD: got sample from track " + this.m_extractor.getSampleTrackIndex() + ", expected " + this.m_videoTrackIndex);
                    }
                    this.m_decoder.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, this.m_extractor.getSampleTime(), 0);
                    this.m_inputBufferQueued = true;
                    this.m_pendingInputFrameCount++;
                    this.m_extractor.advance();
                    this.m_extractorInOriginalState = false;
                }
            }
            int dequeueOutputBuffer = this.m_decoder.dequeueOutputBuffer(this.m_bufferInfo, (long) ((this.m_pendingInputFrameCount > max || this.m_sawInputEOS) ? 4000 : 0));
            i2++;
            if (dequeueOutputBuffer != -1) {
                if (dequeueOutputBuffer == -3) {
                    this.m_decoderOutputBuffers = this.m_decoder.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    ParseMediaFormat(this.m_decoder.getOutputFormat());
                } else if (dequeueOutputBuffer < 0) {
                    Log.e(TAG, "Unexpected result from decoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                    return 2;
                } else {
                    if ((this.m_bufferInfo.flags & 4) != 0) {
                        this.m_sawOutputEOS = true;
                    }
                    if (this.m_bufferInfo.size != 0) {
                        this.m_timestampOfLastDecodedFrame = this.m_bufferInfo.presentationTimeUs;
                        this.m_pendingInputFrameCount--;
                        if (j != Long.MIN_VALUE) {
                            boolean z3 = this.m_timestampOfLastDecodedFrame >= j - j2;
                            if (z3 || this.m_timestampOfLastDecodedFrame < this.m_duration - 100000) {
                                z2 = z3;
                            } else {
                                z2 = true;
                                z = true;
                                i = 0;
                            }
                        } else {
                            z2 = true;
                        }
                        z = false;
                        i = 0;
                    } else {
                        i = i2;
                        z2 = false;
                        z = false;
                    }
                    if (z2) {
                        nativeCopyVideoFrame(this.m_owner, this.m_decoderOutputBuffers[dequeueOutputBuffer], this.m_bufferInfo.offset, this.m_bufferInfo.size, this.m_timestampOfLastDecodedFrame);
                        this.m_timestampOfLastCopiedFrame = this.m_timestampOfLastDecodedFrame;
                    }
                    this.m_decoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if (z2 && !z) {
                        return 0;
                    }
                    i2 = i;
                }
            }
            if (i2 > 100) {
                Log.e(TAG, "We have tried too many times and can't decode a frame!");
                return 2;
            }
        }
        if (j != Long.MIN_VALUE) {
            long j3 = this.m_timestampOfLastCopiedFrame;
            return (j3 == Long.MIN_VALUE || j3 < this.m_duration - 100000) ? 1 : 0;
        }
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

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c8  */
    private void ParseMediaFormat(MediaFormat mediaFormat) {
        int i;
        int i2;
        int i3;
        int i4;
        if (mediaFormat.containsKey(nexExportFormat.TAG_FORMAT_WIDTH) && mediaFormat.containsKey(nexExportFormat.TAG_FORMAT_HEIGHT) && mediaFormat.containsKey("color-format")) {
            int integer = mediaFormat.getInteger(nexExportFormat.TAG_FORMAT_WIDTH);
            int integer2 = mediaFormat.getInteger(nexExportFormat.TAG_FORMAT_HEIGHT);
            int integer3 = mediaFormat.getInteger("color-format");
            if (Build.VERSION.SDK_INT >= 18) {
                String name = this.m_decoder.getName();
                if (integer3 == 25 && name.equals("OMX.k3.video.decoder.avc")) {
                    integer3 = 2130706688;
                }
            }
            int i5 = integer3;
            if (Build.VERSION.SDK_INT >= 23) {
                i2 = mediaFormat.containsKey("slice-height") ? mediaFormat.getInteger("slice-height") : integer2;
                i = mediaFormat.containsKey("stride") ? mediaFormat.getInteger("stride") : integer;
            } else {
                i = integer;
                i2 = integer2;
            }
            if (Build.VERSION.SDK_INT >= 18) {
                String name2 = this.m_decoder.getName();
                if (name2.startsWith("OMX.Nvidia.")) {
                    i2 = (i2 + 15) & -16;
                } else if (name2.startsWith("OMX.SEC.avc.dec")) {
                    i3 = integer;
                    i4 = integer2;
                    int integer4 = !mediaFormat.containsKey("crop-left") ? mediaFormat.getInteger("crop-left") : 0;
                    int integer5 = !mediaFormat.containsKey("crop-right") ? mediaFormat.getInteger("crop-right") : integer - 1;
                    int integer6 = !mediaFormat.containsKey("crop-top") ? mediaFormat.getInteger("crop-top") : 0;
                    int integer7 = !mediaFormat.containsKey("crop-bottom") ? mediaFormat.getInteger("crop-bottom") : integer2 - 1;
                    nativeSetFormatInfo(this.m_owner, (integer5 + 1) - integer4, (integer7 + 1) - integer6, i5, i4, i3, integer4, integer5, integer6, integer7);
                }
            }
            i4 = i2;
            i3 = i;
            if (!mediaFormat.containsKey("crop-left")) {
            }
            int integer52 = !mediaFormat.containsKey("crop-right") ? mediaFormat.getInteger("crop-right") : integer - 1;
            if (!mediaFormat.containsKey("crop-top")) {
            }
            if (!mediaFormat.containsKey("crop-bottom")) {
            }
            nativeSetFormatInfo(this.m_owner, (integer52 + 1) - integer4, (integer7 + 1) - integer6, i5, i4, i3, integer4, integer52, integer6, integer7);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|23|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x003b */
    private int SeekInternal(long j, long j2) {
        long j3 = this.m_timestampOfLastDecodedFrame;
        boolean z = true;
        if ((j3 == Long.MIN_VALUE || j <= j3 || j >= j3 + 1000000) && (!this.m_extractorInOriginalState || j >= 1000000)) {
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
            this.m_decoder.configure(this.m_format, (Surface) null, (MediaCrypto) null, 0);
            this.m_decoder.start();
            this.m_decoderStarted = true;
            this.m_decoderInputBuffers = this.m_decoder.getInputBuffers();
            this.m_decoderOutputBuffers = this.m_decoder.getOutputBuffers();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
            CleanupDecoder();
            return false;
        }
    }

    private native void nativeCopyVideoFrame(long j, ByteBuffer byteBuffer, int i, int i2, long j2);

    private native void nativeSetFormatInfo(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    public void CloseFile() {
        InvalidLastSeekTimestamp();
        CleanupDecoder();
        MediaExtractor mediaExtractor = this.m_extractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.m_extractor = null;
            this.m_videoTrackIndex = -1;
            this.m_format = null;
            this.m_duration = 0;
            this.m_extractorInOriginalState = true;
        }
    }

    public int GetNextVideoFrameForPlayback() {
        if (!IsValid()) {
            return 1;
        }
        int DecodeToFrame = DecodeToFrame(Long.MIN_VALUE, 0);
        InvalidLastSeekTimestamp();
        return DecodeToFrame;
    }

    public boolean OpenFile(String str, AssetManager assetManager, int i) {
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
            int i2 = 0;
            while (true) {
                if (i2 >= trackCount) {
                    break;
                } else if (this.m_extractor.getTrackFormat(i2).getString("mime").startsWith("video/")) {
                    this.m_videoTrackIndex = i2;
                    break;
                } else {
                    i2++;
                }
            }
            int i3 = this.m_videoTrackIndex;
            if (i3 < 0) {
                Log.e(TAG, "Failed to find a video track from " + str);
                CloseFile();
                return false;
            }
            this.m_extractor.selectTrack(i3);
            this.m_format = this.m_extractor.getTrackFormat(this.m_videoTrackIndex);
            if (Build.VERSION.SDK_INT == 16) {
                this.m_format.setInteger("max-input-size", 0);
            }
            if (Build.VERSION.SDK_INT >= 23 && i >= 0) {
                MediaFormat mediaFormat = this.m_format;
                if (i <= 0) {
                    i = 120;
                }
                mediaFormat.setInteger("operating-rate", i);
            }
            this.m_duration = this.m_format.getLong("durationUs");
            String string = this.m_format.getString("mime");
            this.m_decoderSetupFailed = false;
            if (SetupDecoder(string)) {
                return true;
            }
            this.m_decoderSetupFailed = true;
            CloseFile();
            return false;
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
            e.printStackTrace();
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
        long j4 = this.m_timestampOfLastCopiedFrame;
        if (j4 != Long.MIN_VALUE && Math.abs(max - j4) <= j2) {
            return 0;
        }
        int SeekInternal = SeekInternal(max, j2);
        if (SeekInternal == 0) {
            this.m_lastSeekTimestamp = max;
            this.m_lastSeekActualTimestamp = this.m_timestampOfLastCopiedFrame;
        } else {
            InvalidLastSeekTimestamp();
        }
        return SeekInternal;
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
        long j5 = this.m_timestampOfLastCopiedFrame;
        if (max == j5 && j5 == this.m_timestampOfLastDecodedFrame) {
            return 0;
        }
        int SeekInternal = SeekInternal(max, j2);
        InvalidLastSeekTimestamp();
        return SeekInternal;
    }

    public boolean hasDecoderSetupFailed() {
        return this.m_decoderSetupFailed;
    }
}
