package com.nexstreaming.checkcaps;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Checker */
public class a {
    private static String a = "CapChecker";
    private boolean b = false;
    private b c = null;
    /* access modifiers changed from: private */
    public C0003a d;
    private MediaCodec e = null;
    private MediaCodec.BufferInfo f = null;
    private MediaFormat g = null;
    private ByteBuffer[] h = null;

    /* renamed from: com.nexstreaming.checkcaps.a$a  reason: collision with other inner class name */
    /* compiled from: Checker */
    public interface C0003a {
        void a(a aVar, int i);

        void a(a aVar, String str);
    }

    /* compiled from: Checker */
    private static class b extends Thread {
        private a a = null;
        private AbstractMap<String, Object> b = null;

        private b(a aVar, AbstractMap<String, Object> abstractMap) {
            this.a = aVar;
            this.b = abstractMap;
        }

        public static void a(a aVar, AbstractMap<String, Object> abstractMap) {
            new b(aVar, abstractMap).start();
        }

        public void run() {
            AbstractMap<String, Object> abstractMap = this.b;
            if (abstractMap != null && ((Integer) abstractMap.get("command")).intValue() == 1) {
                this.a.d.a(this.a, this.a.b(((Integer) this.b.get(nexExportFormat.TAG_FORMAT_WIDTH)).intValue(), ((Integer) this.b.get(nexExportFormat.TAG_FORMAT_HEIGHT)).intValue()));
            }
        }
    }

    public a(boolean z) {
        this.b = z;
    }

    private static int a(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        int i = 0;
        for (int i2 = 0; i2 < codecCount; i2++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int i3 = 0;
                while (true) {
                    if (i3 >= supportedTypes.length) {
                        break;
                    } else if (supportedTypes[i3].equalsIgnoreCase(str)) {
                        i++;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
        }
        return i;
    }

    @TargetApi(21)
    private MediaCodec a(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat, AtomicReference<Surface> atomicReference) {
        MediaCodec mediaCodec;
        boolean z;
        boolean z2 = true;
        try {
            mediaCodec = MediaCodec.createByCodecName(mediaCodecInfo.getName());
            z = false;
        } catch (IOException e2) {
            e2.printStackTrace();
            z = true;
            mediaCodec = null;
        }
        if (z) {
            return null;
        }
        try {
            mediaCodec.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
            z2 = z;
        } catch (MediaCodec.CodecException unused) {
        } catch (IllegalArgumentException | IllegalStateException e3) {
            e3.printStackTrace();
        }
        if (z2) {
            return null;
        }
        atomicReference.set(mediaCodec.createInputSurface());
        mediaCodec.start();
        return mediaCodec;
    }

    /* access modifiers changed from: private */
    @TargetApi(18)
    public int b(int i, int i2) {
        MediaCodecInfo[] b2 = b("video/avc");
        C0003a aVar = this.d;
        if (aVar != null) {
            aVar.a(this, "The count of 'video/avc' Encoder : " + b2.length);
            for (int i3 = 0; i3 < b2.length; i3++) {
                C0003a aVar2 = this.d;
                aVar2.a(this, i3 + " th encoder's name is '" + b2[i3].getName() + "'");
            }
        }
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", 3000000);
        createVideoFormat.setInteger("frame-rate", 30);
        createVideoFormat.setInteger("i-frame-interval", 2);
        C0003a aVar3 = this.d;
        if (aVar3 != null) {
            aVar3.a(this, "Video Format of Encoder : " + createVideoFormat);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.e = a(b2[0], createVideoFormat, (AtomicReference<Surface>) atomicReference);
        if (this.e == null) {
            return -1;
        }
        this.c = new b((Surface) atomicReference.get());
        this.c.b();
        this.e.stop();
        this.e.release();
        this.e = null;
        this.c.a();
        this.c = null;
        return 0;
    }

    private static MediaCodecInfo[] b(String str) {
        int a2 = a(str);
        MediaCodecInfo[] mediaCodecInfoArr = new MediaCodecInfo[a2];
        if (a2 == 0) {
            return mediaCodecInfoArr;
        }
        int codecCount = MediaCodecList.getCodecCount();
        int i = 0;
        for (int i2 = 0; i2 < codecCount; i2++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i2);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int i3 = 0;
                while (true) {
                    if (i3 >= supportedTypes.length) {
                        break;
                    } else if (supportedTypes[i3].equalsIgnoreCase(str)) {
                        mediaCodecInfoArr[i] = codecInfoAt;
                        i++;
                        break;
                    } else {
                        i3++;
                    }
                }
            }
        }
        return mediaCodecInfoArr;
    }

    public void a(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("command", 1);
        hashMap.put(nexExportFormat.TAG_FORMAT_WIDTH, Integer.valueOf(i));
        hashMap.put(nexExportFormat.TAG_FORMAT_HEIGHT, Integer.valueOf(i2));
        b.a(this, hashMap);
    }

    public void a(C0003a aVar) {
        this.d = aVar;
    }
}
