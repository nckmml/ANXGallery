package com.miui.gallery.util;

import android.util.Patterns;
import com.miui.gallery.preference.BaseGalleryPreferences;
import java.util.Arrays;
import java.util.Locale;

public class Log {
    private static int sLogLevel = 7;

    public static void d(String str, Object obj) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), obj.toString());
        }
    }

    public static void d(String str, String str2) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), str2);
        }
    }

    public static void d(String str, String str2, Object obj) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), logFormat(str2, obj));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), logFormat(str2, obj, obj2));
        }
    }

    public static void d(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), logFormat(str2, obj, obj2, obj3));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (needLog(3)) {
            android.util.Log.d(getFinalTag(str), logFormat(str2, objArr));
        }
    }

    public static void d(String str, Throwable th) {
        if (needLog(3)) {
            d(str, "", (Object) th);
        }
    }

    public static void e(String str, String str2) {
        if (needLog(6)) {
            android.util.Log.e(getFinalTag(str), str2);
        }
    }

    public static void e(String str, String str2, Object obj) {
        if (needLog(6)) {
            android.util.Log.e(getFinalTag(str), logFormat(str2, obj));
        }
    }

    public static void e(String str, String str2, Object obj, Object obj2) {
        if (needLog(6)) {
            android.util.Log.e(getFinalTag(str), logFormat(str2, obj, obj2));
        }
    }

    public static void e(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (needLog(6)) {
            android.util.Log.e(getFinalTag(str), logFormat(str2, obj, obj2, obj3));
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (needLog(6)) {
            android.util.Log.e(getFinalTag(str), logFormat(str2, objArr));
        }
    }

    public static void e(String str, Throwable th) {
        android.util.Log.e(getFinalTag(str), "", th);
    }

    private static String filterSensitiveMsg(String str) {
        return str != null ? Patterns.IP_ADDRESS.matcher(str).replaceAll("*.*.*.*") : str;
    }

    private static String getFinalTag(String str) {
        return "MiuiGallery2" + "_" + str;
    }

    public static void i(String str, String str2) {
        if (needLog(4)) {
            android.util.Log.i(getFinalTag(str), str2);
        }
    }

    public static void i(String str, String str2, Object obj) {
        if (needLog(4)) {
            android.util.Log.i(getFinalTag(str), logFormat(str2, obj));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2) {
        if (needLog(4)) {
            android.util.Log.i(getFinalTag(str), logFormat(str2, obj, obj2));
        }
    }

    public static void i(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (needLog(4)) {
            android.util.Log.i(getFinalTag(str), logFormat(str2, obj, obj2, obj3));
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (needLog(4)) {
            android.util.Log.i(getFinalTag(str), logFormat(str2, objArr));
        }
    }

    private static String logFormat(String str, Object... objArr) {
        String str2 = "";
        if (objArr == null) {
            return str2;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String[]) {
                objArr[i] = prettyArray(objArr[i]);
            }
        }
        try {
            str2 = String.format(Locale.US, str, objArr);
        } catch (Exception e) {
            android.util.Log.d("MiuiGallery2", "log error: the format is \"" + str + "\", the args is: " + Arrays.toString(objArr));
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(str2);
        if (objArr.length > 0 && (objArr[objArr.length - 1] instanceof Throwable)) {
            sb.append(filterSensitiveMsg(android.util.Log.getStackTraceString(objArr[objArr.length - 1])));
        }
        return sb.toString();
    }

    private static boolean needLog(int i) {
        return i >= sLogLevel || BaseGalleryPreferences.Debug.isPrintLog();
    }

    private static String prettyArray(String[] strArr) {
        if (strArr.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            sb.append(", ");
        }
        sb.append(strArr[length]);
        sb.append("]");
        return sb.toString();
    }

    public static void setLogLevel(int i) {
        sLogLevel = i;
    }

    public static void v(String str, String str2) {
        if (needLog(2)) {
            android.util.Log.v(getFinalTag(str), str2);
        }
    }

    public static void v(String str, String str2, Object obj) {
        if (needLog(2)) {
            android.util.Log.v(getFinalTag(str), logFormat(str2, obj));
        }
    }

    public static void v(String str, String str2, Object obj, Object obj2) {
        if (needLog(2)) {
            android.util.Log.v(getFinalTag(str), logFormat(str2, obj, obj2));
        }
    }

    public static void w(String str, String str2) {
        if (needLog(5)) {
            android.util.Log.w(getFinalTag(str), str2);
        }
    }

    public static void w(String str, String str2, Object obj) {
        if (needLog(5)) {
            android.util.Log.w(getFinalTag(str), logFormat(str2, obj));
        }
    }

    public static void w(String str, String str2, Object obj, Object obj2) {
        if (needLog(5)) {
            android.util.Log.w(getFinalTag(str), logFormat(str2, obj, obj2));
        }
    }

    public static void w(String str, String str2, Object obj, Object obj2, Object obj3) {
        if (needLog(5)) {
            android.util.Log.w(getFinalTag(str), logFormat(str2, obj, obj2, obj3));
        }
    }

    public static void w(String str, Throwable th) {
        if (needLog(5)) {
            w(str, "", th);
        }
    }
}
