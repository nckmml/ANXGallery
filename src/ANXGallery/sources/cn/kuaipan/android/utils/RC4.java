package cn.kuaipan.android.utils;

import java.security.InvalidKeyException;

public class RC4 {
    private final int[] sBox = new int[256];
    private int x;
    private int y;

    public void genRC4(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        while (i4 < i2) {
            this.x = (this.x + 1) & 255;
            int[] iArr = this.sBox;
            int i5 = this.x;
            this.y = (iArr[i5] + this.y) & 255;
            int i6 = iArr[i5];
            int i7 = this.y;
            iArr[i5] = iArr[i7];
            iArr[i7] = i6;
            bArr2[i3] = (byte) (bArr[i] ^ iArr[(iArr[i5] + iArr[i7]) & 255]);
            i4++;
            i3++;
            i++;
        }
    }

    public void makeKey(byte[] bArr) throws InvalidKeyException {
        if (bArr != null) {
            int length = bArr.length;
            if (length != 0) {
                this.x = 0;
                this.y = 0;
                for (int i = 0; i < 256; i++) {
                    this.sBox[i] = i;
                }
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < 256; i4++) {
                    int[] iArr = this.sBox;
                    i3 = ((bArr[i2] & 255) + iArr[i4] + i3) & 255;
                    int i5 = iArr[i4];
                    iArr[i4] = iArr[i3];
                    iArr[i3] = i5;
                    i2 = (i2 + 1) % length;
                }
                return;
            }
            throw new InvalidKeyException("Invalid user key length");
        }
        throw new InvalidKeyException("Null user key");
    }

    public void skip(long j) {
        for (long j2 = 0; j2 < j; j2++) {
            this.x = (this.x + 1) & 255;
            int[] iArr = this.sBox;
            int i = this.x;
            this.y = (iArr[i] + this.y) & 255;
            int i2 = iArr[i];
            int i3 = this.y;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }
}
