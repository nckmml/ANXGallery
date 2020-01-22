package com.xiaomi.mistatistic.sdk.controller;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mistatistic.sdk.BuildSetting;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.keyczar.Keyczar;

/* compiled from: Utils */
public class t {
    private static boolean a = true;

    /* compiled from: Utils */
    public enum a {
        TYPE_KEY,
        TYPE_CATEGORY,
        TYPE_VALUE
    }

    private static <T> T a(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get((Object) null);
        } catch (Exception e) {
            j.a("U", "getStaticVariableValue exception", (Throwable) e);
            return null;
        }
    }

    public static String a() {
        if (Build.VERSION.SDK_INT > 8) {
            return Build.SERIAL;
        }
        return null;
    }

    public static String a(Context context, String str) {
        try {
            if ((d(context) && BuildSetting.isInternationalBuild()) || g() || d()) {
                if (!str.toLowerCase().startsWith("http")) {
                    str = "https://" + str;
                }
                String host = new URL(str).getHost();
                if (host.contains("intl.")) {
                    return str;
                }
                if (d(host)) {
                    j.d("U", "The request to the staging environment, do not do international domain transformation");
                    return str;
                }
                String str2 = "";
                if (host.contains(".")) {
                    String[] split = host.split("\\.");
                    if (split != null && split.length > 0) {
                        for (int i = 0; i < split.length; i++) {
                            if (i == split.length - 2) {
                                str2 = str2 + "intl.";
                            }
                            str2 = str2 + split[i];
                            if (i < split.length - 1) {
                                str2 = str2 + ".";
                            }
                        }
                    }
                } else {
                    str2 = "intl." + host;
                }
                return str.replace(host, str2);
            }
        } catch (Exception e) {
            j.a("U", "ensureInternationalServer exception", (Throwable) e);
        }
        return str;
    }

    public static void a(Context context, String str, Map<String, String> map) {
        map.put("app_id", d.b());
        map.put("app_key", d.c());
        map.put("sdk_version", "2.1.0");
        map.put("network", l.f(context));
        map.put("client_config", String.valueOf(n.a(str)));
        if (g(context)) {
            map.put("device_id_tv", g.b());
            map.put("network_tv", l.g(context));
        }
    }

    public static boolean a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        return timeInMillis <= j && j < 86400000 + timeInMillis;
    }

    public static boolean a(long j, long j2) {
        return Math.abs(System.currentTimeMillis() - j) > j2;
    }

    public static boolean a(Context context) {
        boolean z = false;
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.isEmpty()) {
                return false;
            }
            boolean z2 = false;
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                try {
                    if (next.importance == 100 || next.importance == 125) {
                        String[] strArr = next.pkgList;
                        int length = strArr.length;
                        boolean z3 = z2;
                        int i = 0;
                        while (i < length) {
                            try {
                                if (strArr[i].equals(context.getPackageName())) {
                                    z3 = true;
                                }
                                i++;
                            } catch (Throwable th) {
                                th = th;
                                z = z3;
                                j.a("U", "isForegroundRunning exception ", th);
                                return z;
                            }
                        }
                        z2 = z3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = z2;
                    j.a("U", "isForegroundRunning exception ", th);
                    return z;
                }
            }
            return z2;
        } catch (Throwable th3) {
            th = th3;
            j.a("U", "isForegroundRunning exception ", th);
            return z;
        }
    }

    public static boolean a(a aVar, String str) {
        if (aVar == a.TYPE_CATEGORY || aVar == a.TYPE_KEY) {
            if (str.length() > 64) {
                Log.e(j.b(), "The length of category or key is more than 64");
                return false;
            } else if (!h(str)) {
                Log.e(j.b(), "The string contains characters out of a-z/A-Z/0-9/Chinese/_.");
                return false;
            }
        }
        if (aVar != a.TYPE_VALUE || str.length() <= 256) {
            return true;
        }
        Log.e(j.b(), "The length of value is more than 256");
        return false;
    }

    public static boolean a(String str, double d) {
        if (d >= 1.0d) {
            return true;
        }
        if (d <= 0.0d) {
            return false;
        }
        if (d < 9.999999974752427E-7d) {
            d = 9.999999974752427E-7d;
        }
        return Math.abs(str.hashCode()) % 1000000 < ((int) (d * 1000000.0d));
    }

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        try {
            return str.getBytes(Keyczar.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String b(Context context) {
        String f = Build.VERSION.SDK_INT >= 23 ? f() : "";
        if (TextUtils.isEmpty(f)) {
            try {
                f = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                j.a("U", "getMacMd5 exception: ", (Throwable) e);
            }
        }
        return !TextUtils.isEmpty(f) ? b(f) : f;
    }

    public static String b(Context context, String str) {
        if (context == null) {
            return str;
        }
        try {
            if (TextUtils.isEmpty(str)) {
                str = context instanceof Activity ? context.getClass().getName() : "";
            }
            try {
                String packageName = context.getPackageName();
                return str.startsWith(packageName) ? str.replace(packageName, "") : str;
            } catch (Exception e) {
                e = e;
                j.a("transformActName exception", (Throwable) e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
            str = "";
            j.a("transformActName exception", (Throwable) e);
            return str;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(a(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException unused) {
            return str;
        }
    }

    public static boolean b() {
        return a;
    }

    public static String c(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            j.a("U", "getAndroidId exception: ", th);
            return null;
        }
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(a(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException unused) {
            return str;
        }
    }

    public static boolean c() {
        return m.a(d.a(), "non_miui_global_market");
    }

    public static boolean d() {
        try {
            return g(d.a()) && TextUtils.equals(f("ro.mitv.product.overseas"), "true");
        } catch (Exception e) {
            j.a("U", "isMiTvIntlBuild", (Throwable) e);
            return false;
        }
    }

    public static boolean d(Context context) {
        if (m.b(context, "is_miui")) {
            return m.a(context, "is_miui");
        }
        boolean z = false;
        if (g(context)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0);
            z = true;
        } catch (Exception e) {
            j.a("U", "cannot get pkginfo of com.xiaomi.xmsf, not miui.", (Throwable) e);
        }
        m.b(context, "is_miui", z);
        return z;
    }

    public static boolean d(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}").matcher(str).matches();
        } catch (Exception e) {
            j.a("U", "isIp exception", (Throwable) e);
            return false;
        }
    }

    public static String e() {
        try {
            Class<?> cls = Class.forName("mitv.common.ConfigurationManager");
            int parseInt = Integer.parseInt(String.valueOf(cls.getMethod("getProductCategory", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(cls, new Object[0]), new Object[0])));
            Class<?> cls2 = Class.forName("mitv.tv.TvContext");
            return parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITV"))) ? "tv" : parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIBOX"))) ? "box" : parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MITVBOX"))) ? "tvbox" : parseInt == Integer.parseInt(String.valueOf(a(cls2, "PRODUCT_CATEGORY_MIPROJECTOR"))) ? "projector" : "";
        } catch (Exception e) {
            j.a("U", "getMiTvProductCategory exception", (Throwable) e);
            return "";
        }
    }

    public static boolean e(Context context) {
        if (m.b(context, "is_xiaomi")) {
            return m.a(context, "is_xiaomi");
        }
        if (context == null) {
            Log.w(j.d("U"), "!! context is null !!");
        }
        String packageName = context.getPackageName();
        if (packageName.contains("miui") || packageName.contains("xiaomi")) {
            m.b(context, "is_xiaomi", true);
            return true;
        } else if (!d(context)) {
            m.b(context, "is_xiaomi", false);
            return false;
        } else {
            boolean z = (context.getApplicationInfo().flags & 1) != 0;
            j.a("U", "the pkg %s is sys app %s", packageName, Boolean.valueOf(z));
            m.b(context, "is_xiaomi", z);
            return z;
        }
    }

    public static boolean e(String str) {
        return "mistat_basic".equals(str) || "mistat_session".equals(str) || "mistat_pt".equals(str) || "mistat_pv".equals(str) || "mistat_session_extra".equals(str) || "mistat_monitor".equals(str) || "mistat_pa".equals(str);
    }

    private static String f() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                byte[] hardwareAddress = nextElement.getHardwareAddress();
                if (hardwareAddress != null) {
                    if (hardwareAddress.length != 0) {
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        String sb2 = sb.toString();
                        if ("wlan0".equals(nextElement.getName())) {
                            return sb2;
                        }
                    }
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        if (!d(context)) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("miui.telephony.TelephonyManager");
            Method declaredMethod = cls.getDeclaredMethod("getDefault", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke((Object) null, new Object[0]);
            if (invoke == null) {
                return null;
            }
            Method declaredMethod2 = cls.getDeclaredMethod("getMiuiDeviceId", new Class[0]);
            declaredMethod2.setAccessible(true);
            Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
            if (invoke2 == null || !(invoke2 instanceof String)) {
                return null;
            }
            return String.class.cast(invoke2);
        } catch (Exception e) {
            j.a("getMiuiImei exception: ", (Throwable) e);
            return null;
        }
    }

    public static String f(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str});
        } catch (Exception e) {
            j.a("reflectGetSystemProperties exception", (Throwable) e);
            return "";
        }
    }

    public static String g(String str) {
        return TextUtils.isEmpty(str) ? "" : str.contains(",") ? str.replace(",", "") : str;
    }

    private static boolean g() {
        return m.a(d.a(), "enable_global");
    }

    public static boolean g(Context context) {
        if (m.b(context, "is_mitv")) {
            return m.a(context, "is_mitv");
        }
        boolean z = false;
        try {
            if ((context.getPackageManager().getPackageInfo("com.xiaomi.mitv.services", 0).applicationInfo.flags & 1) != 0) {
                z = true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            j.c("Is not Mi Tv system!");
        }
        m.b(context, "is_mitv", z);
        return z;
    }

    private static boolean h(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Pattern.compile("^[a-z0-9A-Z一-龥_]+$").matcher(str).matches();
        } catch (Exception e) {
            j.a("U", "isLetterDigitChineseOrUnderline exception", (Throwable) e);
            return false;
        }
    }
}
