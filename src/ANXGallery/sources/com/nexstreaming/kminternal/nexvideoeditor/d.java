package com.nexstreaming.kminternal.nexvideoeditor;

import android.media.CamcorderProfile;
import com.nexstreaming.kminternal.kinemaster.config.NexEditorDeviceProfile;
import java.util.Vector;

/* compiled from: NexVisualClipChecker */
public class d {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private boolean n = false;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 55;
    private int s = 0;
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private int w = 0;
    private Vector<CamcorderProfile> x;

    d(NexEditor nexEditor) {
        if (nexEditor != null) {
            NexEditorDeviceProfile deviceProfile = NexEditorDeviceProfile.getDeviceProfile();
            int maxCamcorderProfileSizeForUnknownDevice = deviceProfile.getMaxCamcorderProfileSizeForUnknownDevice();
            maxCamcorderProfileSizeForUnknownDevice = maxCamcorderProfileSizeForUnknownDevice <= 0 ? Reader.READ_DONE : maxCamcorderProfileSizeForUnknownDevice;
            this.a = deviceProfile.getIntProperty("Device_Support_BaselineMaxLevel", 0);
            this.b = deviceProfile.getIntProperty("Device_Support_MainMaxLevel", 0);
            this.c = deviceProfile.getIntProperty("Device_Support_HighMaxLevel", 0);
            this.d = deviceProfile.getMCHWAVCDecBaselineSize();
            this.e = deviceProfile.getMCHWAVCDecMainSize();
            this.f = deviceProfile.getMCHWAVCDecHighSize();
            if (deviceProfile.isUnknownDevice()) {
                this.g = a(maxCamcorderProfileSizeForUnknownDevice, nexEditor.b("MCHWAVCEncBaselineLevelSize", 0));
                this.h = Math.min(maxCamcorderProfileSizeForUnknownDevice, nexEditor.b("MCHWAVCEncMainLevelSize", 0));
                this.i = Math.min(maxCamcorderProfileSizeForUnknownDevice, nexEditor.b("MCHWAVCEncHighLevelSize", 0));
            } else {
                this.g = nexEditor.b("MCHWAVCEncBaselineLevelSize", 0);
                this.h = nexEditor.b("MCHWAVCEncMainLevelSize", 0);
                this.i = nexEditor.b("MCHWAVCEncHighLevelSize", 0);
            }
            this.j = nexEditor.a("canUseMCSoftwareCodec", false);
            if (this.j) {
                this.k = deviceProfile.getMCSWAVCDecBaselineSize();
                this.l = deviceProfile.getMCSWAVCDecMainSize();
                this.m = deviceProfile.getMCSWAVCDecHighSize();
            }
            this.n = nexEditor.a("canUseSoftwareCodec", false);
            if (this.n) {
                this.o = deviceProfile.getNXSWAVCDecBaselineSize();
                this.p = deviceProfile.getNXSWAVCDecMainSize();
                this.q = deviceProfile.getNXSWAVCDecHighSize();
            }
            this.r = deviceProfile.getMaxSupportedFPS();
            this.u = deviceProfile.getMaxSupportedVideoBitrate(0);
            this.v = deviceProfile.getMaxSupportedAudioSamplingRate(0);
            this.w = deviceProfile.getMaxSupportedAudioChannels(0);
            if (deviceProfile.isUnknownDevice()) {
                int i2 = this.d;
                this.s = i2;
                this.t = i2;
            } else {
                this.s = deviceProfile.getMaxImportSize(true);
                this.t = deviceProfile.getMaxImportSize(false);
            }
            this.x = new Vector<>();
            int[] iArr = {6, 5, 4, 3, 7};
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (CamcorderProfile.hasProfile(iArr[i3])) {
                    this.x.add(CamcorderProfile.get(iArr[i3]));
                }
            }
        }
    }

    private static int a(int i2, int i3) {
        return i2 == 0 ? i3 : (i3 != 0 && i2 >= i3) ? i3 : i2;
    }

    public int a() {
        return this.g;
    }

    public int a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        int i11;
        int i12;
        int i13 = i2;
        int i14 = i3;
        int i15 = i6;
        int i16 = this.v;
        if (i16 > 0 && i16 < i8) {
            return 6;
        }
        int i17 = this.n ? this.s : this.t;
        if (i13 == 255) {
            int i18 = i4 * i5;
            if (i18 <= i17) {
                return i15 > this.r ? 2 : 0;
            }
            return i18 > (this.n ? this.o : this.d) ? 4 : 1;
        }
        if (this.n) {
            i12 = this.o;
            i11 = this.p;
            i10 = this.q;
        } else {
            i12 = this.d;
            i11 = this.e;
            i10 = this.f;
        }
        if (i13 == 66) {
            int i19 = this.a;
            if (i19 != 0 && i19 < i14) {
                return 5;
            }
            i11 = i12;
        } else if (i13 != 77) {
            if (i13 != 100 || i10 == 0) {
                return 3;
            }
            int i20 = this.c;
            if (i20 != 0 && i20 < i14) {
                return 5;
            }
            i11 = i10;
        } else if (i11 == 0) {
            return 3;
        } else {
            int i21 = this.b;
            if (i21 != 0 && i21 < i14) {
                return 5;
            }
        }
        int i22 = i4 * i5;
        if (i22 > i11) {
            if (this.j) {
                return (i13 != 66 || i22 > this.k) ? 3 : 1;
            }
            return 4;
        } else if (i22 > i17) {
            return 1;
        } else {
            return i15 > this.r ? 2 : 0;
        }
    }
}
