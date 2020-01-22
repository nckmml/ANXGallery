package com.miui.gallery.util;

import java.util.Arrays;

public class UpdateHelper {
    private boolean mUpdated = false;

    public boolean isUpdated() {
        return this.mUpdated;
    }

    public double update(double d, double d2) {
        if (d == d2) {
            return d;
        }
        this.mUpdated = true;
        return d2;
    }

    public int update(int i, int i2) {
        if (i == i2) {
            return i;
        }
        this.mUpdated = true;
        return i2;
    }

    public long update(long j, long j2) {
        if (j == j2) {
            return j;
        }
        this.mUpdated = true;
        return j2;
    }

    public <T> T update(T t, T t2) {
        if (Utils.equals(t, t2)) {
            return t;
        }
        this.mUpdated = true;
        return t2;
    }

    public byte[] update(byte[] bArr, byte[] bArr2) {
        if (Arrays.equals(bArr, bArr2)) {
            return bArr;
        }
        this.mUpdated = true;
        return bArr2;
    }
}
