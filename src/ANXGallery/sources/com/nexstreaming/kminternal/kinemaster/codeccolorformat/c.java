package com.nexstreaming.kminternal.kinemaster.codeccolorformat;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: WrapMediaEncoder */
public class c extends a {
    private d a;
    private MediaCodec b = MediaCodec.createEncoderByType("video/avc");
    private byte[] c;
    private byte[] d;
    private ByteBuffer[] e;
    private ByteBuffer[] f;

    public c() throws IOException {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", 1280, 720);
        createVideoFormat.setInteger("bitrate", 125000);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("color-format", 21);
        createVideoFormat.setInteger("i-frame-interval", 5);
        this.b.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.b.start();
        this.e = this.b.getInputBuffers();
        this.f = this.b.getOutputBuffers();
    }

    public c(int i, int i2) throws IOException {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger("bitrate", 125000);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("color-format", 21);
        createVideoFormat.setInteger("i-frame-interval", 5);
        this.b.configure(createVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.b.start();
        this.e = this.b.getInputBuffers();
        this.f = this.b.getOutputBuffers();
    }

    public void a() throws IOException {
        this.b.stop();
        this.b.release();
    }

    public void a(d dVar) {
        this.a = dVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    public boolean a(byte[] bArr, long j) {
        byte[] bArr2 = bArr;
        MediaCodec mediaCodec = this.b;
        if (mediaCodec == null || this.e == null || this.f == null || this.a == null) {
            Log.w("WrapMediaEncoder", "Media codec did not initailize");
            return false;
        }
        ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
        ByteBuffer[] outputBuffers = this.b.getOutputBuffers();
        int dequeueInputBuffer = this.b.dequeueInputBuffer(-1);
        if (dequeueInputBuffer >= 0) {
            ByteBuffer byteBuffer = inputBuffers[dequeueInputBuffer];
            byteBuffer.clear();
            if (bArr2 != null) {
                byteBuffer.put(bArr2);
                this.b.queueInputBuffer(dequeueInputBuffer, 0, bArr2.length, j, 0);
            } else {
                this.b.queueInputBuffer(dequeueInputBuffer, 0, 0, j, 4);
            }
        }
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int dequeueOutputBuffer = this.b.dequeueOutputBuffer(bufferInfo, 10000);
        byte b2 = 1;
        if (dequeueOutputBuffer != -3) {
            int i = 2;
            if (dequeueOutputBuffer == -2) {
                Log.d("WrapMediaEncoder", "New format " + this.b.getOutputFormat());
                this.a.a(2);
            } else if (dequeueOutputBuffer != -1) {
                while (true) {
                    if (dequeueOutputBuffer < 0) {
                        break;
                    }
                    if ((bufferInfo.flags & 4) != 0) {
                        Log.d("WrapMediaEncoder", "OutputBuffer BUFFER_FLAG_END_OF_STREAM");
                        this.a.a(4);
                    }
                    ByteBuffer byteBuffer2 = outputBuffers[dequeueOutputBuffer];
                    if (byteBuffer2 == null) {
                        Log.w("WrapMediaEncoder", "Output buffer is null!");
                        break;
                    } else if (bufferInfo.size <= 0) {
                        Log.w("WrapMediaEncoder", "Output was not available!");
                        break;
                    } else {
                        Object[] objArr = new Object[i];
                        objArr[0] = Integer.valueOf(bufferInfo.flags);
                        objArr[b2] = Integer.valueOf(bufferInfo.size);
                        Log.d("WrapMediaEncoder", String.format("Output was available Falg:%d Size:%d", objArr));
                        byteBuffer2.position(bufferInfo.offset);
                        byteBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                        if ((bufferInfo.flags & i) != 0) {
                            Log.d("WrapMediaEncoder", "OutputBuffer BUFFER_FLAG_CODEC_CONFIG");
                            byte[] bArr3 = new byte[bufferInfo.size];
                            byteBuffer2.get(bArr3);
                            ByteBuffer wrap = ByteBuffer.wrap(bArr3);
                            if (wrap.getInt() == b2) {
                                System.out.println("parsing sps/pps");
                            } else {
                                System.out.println("something is amiss?");
                            }
                            while (true) {
                                if (wrap.get() == 0 && wrap.get() == 0 && wrap.get() == 0 && wrap.get() == b2) {
                                    break;
                                }
                            }
                            int position = wrap.position();
                            this.c = new byte[((position - 8) + 4)];
                            byte[] bArr4 = this.c;
                            bArr4[0] = 0;
                            bArr4[b2] = 0;
                            bArr4[i] = 0;
                            bArr4[3] = b2;
                            System.arraycopy(bArr3, 4, bArr4, 4, bArr4.length - 4);
                            this.d = new byte[((bArr3.length - position) + 4)];
                            byte[] bArr5 = this.d;
                            bArr5[0] = 0;
                            bArr5[b2] = 0;
                            bArr5[i] = 0;
                            bArr5[3] = b2;
                            System.arraycopy(bArr3, position, bArr5, 4, bArr5.length - 4);
                            this.a.a(this.c, this.d);
                            this.b.releaseOutputBuffer(dequeueOutputBuffer, false);
                            dequeueOutputBuffer = this.b.dequeueOutputBuffer(bufferInfo, 10000);
                        } else {
                            byte[] bArr6 = new byte[bufferInfo.size];
                            byteBuffer2.get(bArr6);
                            this.a.a(bArr6, 0, bArr6.length, bufferInfo.presentationTimeUs);
                            this.b.releaseOutputBuffer(dequeueOutputBuffer, false);
                            dequeueOutputBuffer = this.b.dequeueOutputBuffer(bufferInfo, 10000);
                            b2 = 1;
                            i = 2;
                        }
                    }
                }
                return b2;
            } else {
                Log.d("WrapMediaEncoder", "dequeueOutputBuffer timed out!");
                this.a.a(3);
            }
            return true;
        }
        Log.d("WrapMediaEncoder", "INFO_OUTPUT_BUFFERS_CHANGED");
        this.b.getOutputBuffers();
        this.a.a(1);
        return true;
    }
}
