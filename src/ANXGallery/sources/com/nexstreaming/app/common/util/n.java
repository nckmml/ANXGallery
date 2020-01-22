package com.nexstreaming.app.common.util;

import android.content.Context;
import android.content.res.Resources;
import java.util.Locale;
import java.util.Map;

/* compiled from: StringUtil */
public class n {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String a(Context context, String str) {
        int identifier;
        if (!(str == null || context == null)) {
            if (str.startsWith("@string/")) {
                Resources resources = context.getResources();
                int identifier2 = resources.getIdentifier("string/kedl_" + str.substring(8), "string", context.getPackageName());
                if (identifier2 != 0) {
                    return context.getResources().getString(identifier2);
                }
            } else if (str.startsWith("@") && (identifier = context.getResources().getIdentifier(str.substring(1), "string", context.getPackageName())) != 0) {
                return context.getResources().getString(identifier);
            }
        }
        return str;
    }

    public static String a(Context context, Map<String, String> map) {
        return a(context, b(context, map, (String) null));
    }

    public static String a(Context context, Map<String, String> map, String str) {
        return a(context, b(context, map, str));
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            char[] cArr2 = a;
            cArr[i2] = cArr2[(bArr[i] & 240) >> 4];
            cArr[i2 + 1] = cArr2[bArr[i] & 15];
        }
        return new String(cArr);
    }

    private static String b(Context context, Map<String, String> map, String str) {
        String str2;
        Locale locale = context != null ? context.getResources().getConfiguration().locale : null;
        if (map == null) {
            return null;
        }
        if (locale == null) {
            locale = Locale.ENGLISH;
        }
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        String lowerCase3 = locale.getVariant().toLowerCase(Locale.ENGLISH);
        if (lowerCase3.isEmpty() && lowerCase2.isEmpty()) {
            str2 = lowerCase;
        } else if (lowerCase3.isEmpty()) {
            str2 = lowerCase + "-" + lowerCase2;
        } else if (lowerCase2.isEmpty()) {
            str2 = lowerCase + "-" + lowerCase3;
        } else {
            str2 = lowerCase + "-" + lowerCase2 + "-" + lowerCase3;
        }
        String str3 = map.get(str2);
        if (str3 != null) {
            return str3;
        }
        String str4 = map.get(lowerCase);
        if (str4 != null) {
            return str4;
        }
        String str5 = map.get("");
        if (str5 != null) {
            return str5;
        }
        String str6 = map.get("en");
        if (str6 != null) {
            return str6;
        }
        String str7 = map.get("en-us");
        return str7 == null ? str : str7;
    }
}
