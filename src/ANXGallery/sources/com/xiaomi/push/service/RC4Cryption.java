package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import org.keyczar.Keyczar;

public class RC4Cryption {
    private static int keylength = 8;
    private byte[] S = new byte[256];
    private int next_j = -666;
    private int the_i = 0;
    private int the_j = 0;

    public static byte[] decrypt(byte[] bArr, String str) {
        return encrypt(bArr, Base64Coder.decode(str));
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        RC4Cryption rC4Cryption = new RC4Cryption();
        rC4Cryption.ksa(bArr);
        rC4Cryption.init();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ rC4Cryption.nextVal());
        }
        return bArr3;
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2, boolean z, int i, int i2) {
        byte[] bArr3;
        int i3;
        if (i < 0 || i > bArr2.length || i + i2 > bArr2.length) {
            throw new IllegalArgumentException("start = " + i + " len = " + i2);
        }
        if (!z) {
            bArr3 = new byte[i2];
            i3 = 0;
        } else {
            bArr3 = bArr2;
            i3 = i;
        }
        RC4Cryption rC4Cryption = new RC4Cryption();
        rC4Cryption.ksa(bArr);
        rC4Cryption.init();
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i3 + i4] = (byte) (bArr2[i + i4] ^ rC4Cryption.nextVal());
        }
        return bArr3;
    }

    public static byte[] generateKeyForRC4(String str, String str2) {
        byte[] decode = Base64Coder.decode(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[(decode.length + 1 + bytes.length)];
        for (int i = 0; i < decode.length; i++) {
            bArr[i] = decode[i];
        }
        bArr[decode.length] = 95;
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr[decode.length + 1 + i2] = bytes[i2];
        }
        return bArr;
    }

    private void init() {
        this.the_j = 0;
        this.the_i = 0;
    }

    private void ksa(int i, byte[] bArr, boolean z) {
        int length = bArr.length;
        for (int i2 = 0; i2 < 256; i2++) {
            this.S[i2] = (byte) i2;
        }
        this.the_j = 0;
        this.the_i = 0;
        while (true) {
            int i3 = this.the_i;
            if (i3 >= i) {
                break;
            }
            this.the_j = ((this.the_j + posify(this.S[i3])) + posify(bArr[this.the_i % length])) % 256;
            sswap(this.S, this.the_i, this.the_j);
            this.the_i++;
        }
        if (i != 256) {
            this.next_j = ((this.the_j + posify(this.S[i])) + posify(bArr[i % length])) % 256;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i4 = i - 1;
            sb.append(i4);
            sb.append(":");
            for (int i5 = 0; i5 <= i; i5++) {
                sb.append(" ");
                sb.append(posify(this.S[i5]));
            }
            sb.append("   j_");
            sb.append(i4);
            sb.append("=");
            sb.append(this.the_j);
            sb.append("   j_");
            sb.append(i);
            sb.append("=");
            sb.append(this.next_j);
            sb.append("   S_");
            sb.append(i4);
            sb.append("[j_");
            sb.append(i4);
            sb.append("]=");
            sb.append(posify(this.S[this.the_j]));
            sb.append("   S_");
            sb.append(i4);
            sb.append("[j_");
            sb.append(i);
            sb.append("]=");
            sb.append(posify(this.S[this.next_j]));
            if (this.S[1] != 0) {
                sb.append("   S[1]!=0");
            }
            MyLog.w(sb.toString());
        }
    }

    private void ksa(byte[] bArr) {
        ksa(256, bArr, false);
    }

    public static int posify(byte b) {
        return b >= 0 ? b : b + Keyczar.FORMAT_VERSION;
    }

    private static void sswap(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    /* access modifiers changed from: package-private */
    public byte nextVal() {
        this.the_i = (this.the_i + 1) % 256;
        this.the_j = (this.the_j + posify(this.S[this.the_i])) % 256;
        sswap(this.S, this.the_i, this.the_j);
        byte[] bArr = this.S;
        return bArr[(posify(bArr[this.the_i]) + posify(this.S[this.the_j])) % 256];
    }
}
