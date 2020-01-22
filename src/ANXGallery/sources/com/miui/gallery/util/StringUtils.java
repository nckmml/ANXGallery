package com.miui.gallery.util;

import android.text.TextUtils;

public class StringUtils {
    public static boolean equalsIgnoreCase(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return TextUtils.equals(str.toLowerCase(), str2.toLowerCase());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isValid(String[] strArr) {
        return strArr != null && strArr.length > 0;
    }

    public static String join(String str, long[] jArr) {
        if (jArr == null || jArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (long j : jArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(j);
        }
        return sb.toString();
    }

    public static String[] mergeStringArray(String[] strArr, String[] strArr2) {
        if (strArr == null) {
            return strArr2;
        }
        if (strArr2 == null) {
            return strArr;
        }
        String[] strArr3 = new String[(strArr.length + strArr2.length)];
        System.arraycopy(strArr, 0, strArr3, 0, strArr.length);
        System.arraycopy(strArr2, 0, strArr3, strArr.length, strArr2.length);
        return strArr3;
    }

    public static String nullToEmpty(CharSequence charSequence) {
        return charSequence == null ? "" : charSequence.toString();
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
