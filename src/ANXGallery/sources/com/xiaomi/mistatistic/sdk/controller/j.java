package com.xiaomi.mistatistic.sdk.controller;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/* compiled from: Logger */
public class j {
    private static boolean a;

    private static String a(String str, Object... objArr) {
        try {
            return String.format(Locale.getDefault(), str, objArr);
        } catch (Exception e) {
            a("log getMessage exception :", (Throwable) e);
            return null;
        }
    }

    private static void a(int i, Throwable th, String str, String str2) {
        String e = e(str);
        if (i == 0) {
            Log.e(e, str2, th);
        } else if (i == 1) {
            Log.w(e, str2, th);
        } else if (i == 2) {
            Log.i(e, str2, th);
        } else if (i == 3) {
            Log.d(e, str2, th);
        } else if (i == 4) {
            Log.v(e, str2, th);
        }
    }

    private static void a(int i, Throwable th, String str, String str2, Object... objArr) {
        String e = e(str);
        String a2 = a(str2, objArr);
        if (i == 0) {
            Log.e(e, a2, th);
        } else if (i == 1) {
            Log.w(e, a2, th);
        } else if (i == 2) {
            Log.i(e, a2, th);
        } else if (i == 3) {
            Log.d(e, a2, th);
        } else if (i == 4) {
            Log.v(e, a2, th);
        }
    }

    public static void a(String str) {
        if (a) {
            a(4, (Throwable) null, (String) null, str);
        }
    }

    public static void a(String str, String str2) {
        if (a) {
            a(4, (Throwable) null, str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        a(0, th, str, str2);
    }

    public static void a(String str, String str2, Object... objArr) {
        if (a) {
            a(3, (Throwable) null, str, str2, objArr);
        }
    }

    public static void a(String str, Throwable th) {
        a(0, th, (String) null, str);
    }

    public static boolean a() {
        return a;
    }

    public static String b() {
        return "MI_STAT";
    }

    public static void b(String str) {
        if (a) {
            a(3, (Throwable) null, (String) null, str);
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            a(3, (Throwable) null, str, str2);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (a) {
            a(2, (Throwable) null, str, str2, objArr);
        }
    }

    public static void c(String str) {
        if (a) {
            a(1, (Throwable) null, (String) null, str);
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            a(2, (Throwable) null, str, str2);
        }
    }

    public static String d(String str) {
        return "MI_STAT_" + str;
    }

    public static void d(String str, String str2) {
        if (a) {
            a(1, (Throwable) null, str, str2);
        }
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "MI_STAT";
        }
        return "MI_STAT_" + str;
    }
}
