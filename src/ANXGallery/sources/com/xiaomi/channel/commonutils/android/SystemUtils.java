package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.reflect.JavaCalls;

public class SystemUtils {
    private static String cachedMOSVersion;
    private static Context sContext;

    private static String getCOSVersion() {
        String str = SystemProperties.get("ro.build.version.opporom", "");
        if (!TextUtils.isEmpty(str) && !str.startsWith("ColorOS_")) {
            cachedMOSVersion = "ColorOS_" + str;
        }
        return cachedMOSVersion;
    }

    public static Context getContext() {
        return sContext;
    }

    private static String getEMUIVersion() {
        cachedMOSVersion = SystemProperties.get("ro.build.version.emui", "");
        return cachedMOSVersion;
    }

    private static String getFOSVersion() {
        String str = SystemProperties.get("ro.vivo.os.version", "");
        if (!TextUtils.isEmpty(str) && !str.startsWith("FuntouchOS_")) {
            cachedMOSVersion = "FuntouchOS_" + str;
        }
        return cachedMOSVersion;
    }

    public static String getMIID(Context context) {
        if (MIUIUtils.isNotMIUI()) {
            return "";
        }
        String str = (String) JavaCalls.callStaticMethod("com.xiaomi.xmsf.helper.MIIDAccountHelper", "getMIID", context);
        return TextUtils.isEmpty(str) ? MovieStatUtils.DOWNLOAD_SUCCESS : str;
    }

    public static int getMIUIType() {
        try {
            Class<?> loadClass = loadClass((Context) null, "android.os.Build");
            if (loadClass.getField("IS_STABLE_VERSION").getBoolean((Object) null)) {
                return 3;
            }
            return loadClass.getField("IS_DEVELOPMENT_VERSION").getBoolean((Object) null) ? 2 : 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static synchronized String getManufacturerOSVersion() {
        String str;
        synchronized (SystemUtils.class) {
            if (cachedMOSVersion != null) {
                String str2 = cachedMOSVersion;
                return str2;
            }
            String str3 = Build.VERSION.INCREMENTAL;
            if (getMIUIType() <= 0) {
                str = getEMUIVersion();
                if (TextUtils.isEmpty(str)) {
                    str = getCOSVersion();
                    if (TextUtils.isEmpty(str)) {
                        str = getFOSVersion();
                        if (TextUtils.isEmpty(str)) {
                            str3 = String.valueOf(SystemProperties.get("ro.product.brand", "Android") + "_" + str3);
                        }
                    }
                }
                cachedMOSVersion = str;
                return str;
            }
            str = str3;
            cachedMOSVersion = str;
            return str;
        }
    }

    public static void initialize(Context context) {
        sContext = context.getApplicationContext();
    }

    public static boolean isBootCompleted() {
        return TextUtils.equals((String) JavaCalls.callStaticMethod("android.os.SystemProperties", "get", "sys.boot_completed"), "1");
    }

    public static boolean isDebuggable(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            MyLog.e((Throwable) e);
            return false;
        }
    }

    public static boolean isGlobalVersion() {
        try {
            return loadClass((Context) null, "android.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(false);
        } catch (ClassNotFoundException unused) {
            MyLog.e("android.os.Build ClassNotFound");
            return false;
        } catch (Exception e) {
            MyLog.e((Throwable) e);
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.w(java.lang.String.format("loadClass fail hasContext= %s, errMsg = %s", new java.lang.Object[]{java.lang.Boolean.valueOf(r2), r5.getLocalizedMessage()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        throw new java.lang.ClassNotFoundException("loadClass fail ", r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0024 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0024 A[SYNTHETIC, Splitter:B:13:0x0024] */
    public static Class<?> loadClass(Context context, String str) throws ClassNotFoundException {
        if (str == null || str.trim().length() == 0) {
            throw new ClassNotFoundException("class is empty");
        }
        boolean z = context != null;
        return (!z || Build.VERSION.SDK_INT < 29) ? Class.forName(str) : context.getClassLoader().loadClass(str);
    }
}
