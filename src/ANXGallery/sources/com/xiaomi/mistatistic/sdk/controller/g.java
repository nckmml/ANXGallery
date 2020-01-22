package com.xiaomi.mistatistic.sdk.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import com.xiaomi.mistatistic.sdk.controller.e;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/* compiled from: DeviceIdHolder */
public class g {
    private static final Object a = new Object();
    private static final Object b = new Object();
    private static final Object c = new Object();
    private static volatile String d = null;
    private static volatile String e = null;
    private static volatile String f = null;
    private static String g = null;

    public static String a() {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        e.a().a((e.a) new e.a() {
            public void execute() {
                String unused = g.e(d.a());
            }
        });
        return null;
    }

    public static String a(Context context) {
        return !TextUtils.isEmpty(d) ? d : e(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046 A[SYNTHETIC, Splitter:B:21:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c A[SYNTHETIC, Splitter:B:25:0x004c] */
    private static String a(String str) {
        String str2 = "";
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(str)), 512);
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    str2 = str2 + readLine;
                }
                try {
                    bufferedReader2.close();
                } catch (Exception e2) {
                    j.a("DIH", "catEntry finally exception", (Throwable) e2);
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = bufferedReader2;
                try {
                    j.a("DIH", "catEntry exception", (Throwable) e);
                    if (bufferedReader != null) {
                    }
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e4) {
                        j.a("DIH", "catEntry finally exception", (Throwable) e4);
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            j.a("DIH", "catEntry exception", (Throwable) e);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return str2;
        }
        return str2;
    }

    public static String b() {
        if (TextUtils.isEmpty(e)) {
            synchronized (b) {
                try {
                    String b2 = t.b(c());
                    if (b2 != null && b2.length() > 0) {
                        e = b2;
                    }
                } catch (Exception e2) {
                    j.a("DIH", "getDeviceIdTv exception", (Throwable) e2);
                }
            }
        }
        return e;
    }

    public static String b(Context context) {
        String str = "";
        String a2 = m.a(context, "imei_md5", str);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String f2 = f(context);
        if (!TextUtils.isEmpty(f2)) {
            str = t.b(f2);
            if (!TextUtils.isEmpty(str)) {
                m.b(context, "imei_md5", str);
            }
        }
        return str;
    }

    private static String c() {
        try {
            boolean z = true;
            if (!TextUtils.equals("tv", t.e())) {
                String str = Build.PRODUCT;
                if (!"augustrush".equals(str)) {
                    if (!"casablanca".equals(str)) {
                        z = false;
                    }
                }
            }
            String a2 = z ? a("/sys/class/net/eth0/address") : t.f("ro.boot.btmac");
            return (a2 == null || a2.length() <= 0) ? a2 : a2.trim();
        } catch (Exception e2) {
            j.a("DIH", "getDeviceMacTv exception", (Throwable) e2);
            return null;
        }
    }

    public static synchronized String c(Context context) {
        synchronized (g.class) {
            if (!TextUtils.isEmpty(g)) {
                String str = g;
                return str;
            }
            long currentTimeMillis = System.currentTimeMillis();
            String a2 = m.a(context, "anonymous_id", "");
            long a3 = m.a(context, "aigt", 0);
            long a4 = m.a(context, "anonymous_ei", 7776000000L);
            if (!TextUtils.isEmpty(a2)) {
                if (currentTimeMillis - a3 < a4) {
                    g = a2;
                    m.b(context, "aigt", currentTimeMillis);
                    String str2 = g;
                    return str2;
                }
            }
            g = UUID.randomUUID().toString();
            m.b(context, "anonymous_id", g);
            m.b(context, "aigt", currentTimeMillis);
            String str22 = g;
            return str22;
        }
    }

    /* access modifiers changed from: private */
    public static String e(Context context) {
        synchronized (a) {
            if (TextUtils.isEmpty(d)) {
                try {
                    Context applicationContext = context.getApplicationContext();
                    String a2 = m.a(applicationContext, "device_id", "");
                    if (TextUtils.isEmpty(a2)) {
                        if (!BuildSetting.isInternationalBuild()) {
                            if (!t.c()) {
                                String c2 = t.c(applicationContext);
                                String a3 = t.a();
                                d = t.c(f(context) + c2 + a3);
                                m.b(applicationContext, "device_id", d);
                            }
                        }
                        String b2 = b(context);
                        if (!TextUtils.isEmpty(b2)) {
                            d = b2;
                        } else {
                            d = c(context);
                        }
                        m.b(applicationContext, "device_id", d);
                    } else {
                        d = a2;
                    }
                } catch (Exception e2) {
                    j.a("DIH", "getDeviceId exception", (Throwable) e2);
                }
            }
        }
        return d;
    }

    @TargetApi(9)
    private static String f(Context context) {
        if (TextUtils.isEmpty(f)) {
            synchronized (c) {
                if (TextUtils.isEmpty(f)) {
                    try {
                        if (TextUtils.isEmpty(f)) {
                            if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                                f = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                            } else {
                                j.d("DIH", "cannot get READ_PHONE_STATE permission");
                            }
                        }
                    } catch (Exception e2) {
                        j.a("DIH", "getImei exception:", (Throwable) e2);
                    }
                }
            }
        }
        if (TextUtils.isEmpty(f)) {
            j.c("DIH", "Imei is empty");
        }
        return f;
    }
}
