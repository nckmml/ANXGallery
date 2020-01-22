package com.nexstreaming.app.common.util;

/* compiled from: ColorUtil */
public class c {
    public static int a(String str) {
        String trim = str.trim();
        if (trim.startsWith("#")) {
            String trim2 = trim.substring(1).trim();
            if (trim2.length() == 3) {
                long parseLong = Long.parseLong(trim2, 16);
                return ((int) (((parseLong & 3840) << 12) | ((15 & parseLong) << 4) | ((240 & parseLong) << 8))) | -16777216;
            } else if (trim2.length() == 6) {
                return ((int) Long.parseLong(trim2, 16)) | -16777216;
            } else {
                if (trim2.length() < 8) {
                    return 0;
                }
                long parseLong2 = Long.parseLong(trim2, 16);
                return (int) (((parseLong2 & 255) << 24) | (parseLong2 >> 8));
            }
        } else {
            String[] split = trim.trim().split(" ");
            int i = 2;
            int i2 = 0;
            for (String trim3 : split) {
                String trim4 = trim3.trim();
                if (trim4.length() >= 1) {
                    int parseFloat = (int) (Float.parseFloat(trim4) * 255.0f);
                    if (parseFloat > 255) {
                        parseFloat = 255;
                    }
                    if (parseFloat < 0) {
                        parseFloat = 0;
                    }
                    if (i < 0) {
                        return i2 | (parseFloat << 24);
                    }
                    i2 |= parseFloat << (i * 8);
                    i--;
                }
            }
            return i2;
        }
    }

    public static String a(int i) {
        return String.format("#%08X", new Object[]{Integer.valueOf((i << 8) | (i >>> 24))});
    }
}
