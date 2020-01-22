package com.xiaomi.channel.commonutils.misc;

import android.content.Context;

public class DebugUtils {
    static final char[] HEXCHARS = "0123456789ABCDEF".toCharArray();

    public static String bytes2Hex(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 * 2);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3] & 255;
            sb.append(HEXCHARS[b >> 4]);
            sb.append(HEXCHARS[b & 15]);
        }
        return sb.toString();
    }

    public static boolean isTesting(Context context) {
        return DebugSwitch.sDebugServerHost;
    }
}
