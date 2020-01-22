package com.xiaomi.channel.commonutils.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.FileLocker;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.reflect.JavaCalls;
import com.xiaomi.channel.commonutils.string.XMStringUtils;
import java.io.File;
import java.io.IOException;

public class DeviceInfo {
    private static String sCachedDeviceId = null;
    private static String sCachedIMEI = null;
    private static String sCachedSimpleDeviceId = null;
    private static String sCachedSubIMEIS = "";
    private static volatile boolean sVirtDevIDChecked;
    private static String sVirtDevId;

    public static String blockingGetIMEI(Context context) {
        String quicklyGetIMEI = quicklyGetIMEI(context);
        int i = 10;
        while (quicklyGetIMEI == null) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            quicklyGetIMEI = quicklyGetIMEI(context);
            i = i2;
        }
        return quicklyGetIMEI;
    }

    private static String blockingGetIMEIWhenDeviceRegister(Context context) {
        String quicklyGetIMEI = quicklyGetIMEI(context);
        int i = 10;
        while (TextUtils.isEmpty(quicklyGetIMEI)) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            quicklyGetIMEI = quicklyGetIMEI(context);
            i = i2;
        }
        return quicklyGetIMEI;
    }

    public static String blockingGetSubIMEISMd5(Context context) {
        String quicklyGetSubIMEISMd5 = quicklyGetSubIMEISMd5(context);
        int i = 10;
        while (quicklyGetSubIMEISMd5 == null) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
            quicklyGetSubIMEISMd5 = quicklyGetSubIMEISMd5(context);
            i = i2;
        }
        return quicklyGetSubIMEISMd5;
    }

    private static boolean canReadPhoneState(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c7  */
    public static String checkVirtDevId(Context context) {
        FileLocker fileLocker;
        String str;
        if (!isSupportVDevid(context) || sVirtDevIDChecked) {
            return null;
        }
        sVirtDevIDChecked = true;
        String fileToStr = IOUtils.fileToStr(new File(context.getFilesDir(), ".vdevid"));
        try {
            File file = new File(new File(Environment.getExternalStorageDirectory(), "/Xiaomi/"), ".vdevid");
            fileLocker = FileLocker.lock(context, file);
            try {
                str = IOUtils.fileToStr(file);
                if (fileLocker != null) {
                    fileLocker.unlock();
                }
            } catch (IOException e) {
                e = e;
                try {
                    MyLog.w("check id failure :" + e.getMessage());
                    if (fileLocker != null) {
                    }
                    str = null;
                    if (!TextUtils.isEmpty(fileToStr)) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileLocker != null) {
                        fileLocker.unlock();
                    }
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            fileLocker = null;
            MyLog.w("check id failure :" + e.getMessage());
            if (fileLocker != null) {
                fileLocker.unlock();
            }
            str = null;
            if (!TextUtils.isEmpty(fileToStr)) {
            }
        } catch (Throwable th2) {
            th = th2;
            fileLocker = null;
            if (fileLocker != null) {
            }
            throw th;
        }
        if (!TextUtils.isEmpty(fileToStr)) {
            sVirtDevId = fileToStr;
            if (TextUtils.isEmpty(str) || str.length() > 128) {
                MyLog.w("recover vid :" + str);
                updateVirtDevId(context, fileToStr);
            } else if (!TextUtils.equals(fileToStr, str)) {
                MyLog.w("vid changed, need sync");
                return str;
            }
            MyLog.v("vdevid = " + sVirtDevId + " " + str);
            return null;
        }
        MyLog.w("empty local vid");
        return "F*";
    }

    public static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            MyLog.e(th);
            return null;
        }
    }

    public static String getDeviceId(Context context, boolean z) {
        if (sCachedDeviceId == null) {
            String blockingGetIMEI = !MIUIUtils.isGlobalRegion() ? z ? blockingGetIMEI(context) : blockingGetIMEIWhenDeviceRegister(context) : "";
            String androidId = getAndroidId(context);
            String serialNum = getSerialNum();
            StringBuilder sb = new StringBuilder();
            sb.append("a-");
            sb.append(XMStringUtils.getSHA1Digest(blockingGetIMEI + androidId + serialNum));
            sCachedDeviceId = sb.toString();
        }
        return sCachedDeviceId;
    }

    public static String getDeviceId1(Context context) {
        String androidId = getAndroidId(context);
        return "a-" + XMStringUtils.getSHA1Digest(null + androidId + null);
    }

    public static String getGaid(Context context) {
        try {
            return GoogleAdvertisingClient.getAdvertisingIdInfo(context).getId();
        } catch (Exception e) {
            MyLog.w("failure to get gaid:" + e.getMessage());
            return null;
        }
    }

    public static synchronized String getInstanceId(Context context) {
        String sHA1Digest;
        synchronized (DeviceInfo.class) {
            String androidId = getAndroidId(context);
            sHA1Digest = XMStringUtils.getSHA1Digest(androidId + null);
        }
        return sHA1Digest;
    }

    public static String getMacAddress(Context context) {
        return "";
    }

    public static String getSerialNum() {
        if (Build.VERSION.SDK_INT > 8 && Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return (String) JavaCalls.callStaticMethod("android.os.Build", "getSerial", (Object[]) null);
        }
        return null;
    }

    public static String getSimOperatorName(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static synchronized String getSimpleDeviceId(Context context) {
        synchronized (DeviceInfo.class) {
            if (sCachedSimpleDeviceId != null) {
                String str = sCachedSimpleDeviceId;
                return str;
            }
            String androidId = getAndroidId(context);
            String serialNum = getSerialNum();
            sCachedSimpleDeviceId = XMStringUtils.getSHA1Digest(androidId + serialNum);
            String str2 = sCachedSimpleDeviceId;
            return str2;
        }
    }

    @TargetApi(17)
    public static int getSpaceId() {
        Object callStaticMethod;
        if (Build.VERSION.SDK_INT >= 17 && (callStaticMethod = JavaCalls.callStaticMethod("android.os.UserHandle", "myUserId", new Object[0])) != null) {
            return Integer.class.cast(callStaticMethod).intValue();
        }
        return -1;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String, com.xiaomi.channel.commonutils.file.FileLocker] */
    public static String getVirtDevId(Context context) {
        ? r1 = 0;
        if (!isSupportVDevid(context)) {
            return r1;
        }
        if (!TextUtils.isEmpty(sVirtDevId)) {
            return sVirtDevId;
        }
        sVirtDevId = IOUtils.fileToStr(new File(context.getFilesDir(), ".vdevid"));
        if (!TextUtils.isEmpty(sVirtDevId)) {
            return sVirtDevId;
        }
        try {
            File file = new File(new File(Environment.getExternalStorageDirectory(), "/Xiaomi/"), ".vdevid");
            FileLocker lock = FileLocker.lock(context, file);
            sVirtDevId = "";
            String fileToStr = IOUtils.fileToStr(file);
            if (fileToStr != null) {
                sVirtDevId = fileToStr;
            }
            String str = sVirtDevId;
            if (lock != null) {
                lock.unlock();
            }
            return str;
        } catch (IOException e) {
            MyLog.w("getVDevID failure :" + e.getMessage());
            if (r1 != 0) {
                r1.unlock();
            }
            return sVirtDevId;
        } catch (Throwable th) {
            if (r1 != 0) {
                r1.unlock();
            }
            throw th;
        }
    }

    private static boolean isSupportVDevid(Context context) {
        boolean z = false;
        if (!PermissionUtils.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || MIUIUtils.isMIUI()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            z = true;
        }
        return !z ? SystemUtils.isDebuggable(context) : z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r2 = com.xiaomi.channel.commonutils.reflect.JavaCalls.callMethod((r2 = com.xiaomi.channel.commonutils.reflect.JavaCalls.callStaticMethod("miui.telephony.TelephonyManager", "getDefault", new java.lang.Object[0])), "getMiuiDeviceId", new java.lang.Object[0]);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    public static String quicklyGetIMEI(Context context) {
        Object callStaticMethod;
        Object callMethod;
        if (MIUIUtils.isGlobalRegion()) {
            return "";
        }
        String str = sCachedIMEI;
        if (str != null) {
            return str;
        }
        try {
            String cast = (!MIUIUtils.isMIUI() || callStaticMethod == null || callMethod == null || !(callMethod instanceof String)) ? null : String.class.cast(callMethod);
            if (cast == null && canReadPhoneState(context)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (Build.VERSION.SDK_INT < 26) {
                    cast = telephonyManager.getDeviceId();
                } else if (1 == telephonyManager.getPhoneType()) {
                    cast = JavaCalls.callMethod(telephonyManager, "getImei", (Object[]) null);
                } else if (2 == telephonyManager.getPhoneType()) {
                    cast = JavaCalls.callMethod(telephonyManager, "getMeid", (Object[]) null);
                }
            }
            if (!verifyImei(cast)) {
                return "";
            }
            sCachedIMEI = cast;
            return cast;
        } catch (Throwable th) {
            MyLog.e(th);
            return null;
        }
    }

    public static String quicklyGetSubIMEIS(Context context) {
        if (MIUIUtils.isGlobalRegion() || Build.VERSION.SDK_INT < 22) {
            return "";
        }
        if (!TextUtils.isEmpty(sCachedSubIMEIS)) {
            return sCachedSubIMEIS;
        }
        if (!canReadPhoneState(context)) {
            return "";
        }
        quicklyGetIMEI(context);
        if (TextUtils.isEmpty(sCachedIMEI)) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Integer num = (Integer) JavaCalls.callMethod(telephonyManager, "getPhoneCount", new Object[0]);
            if (num == null || num.intValue() <= 1) {
                return "";
            }
            String str = null;
            for (int i = 0; i < num.intValue(); i++) {
                if (Build.VERSION.SDK_INT < 26) {
                    str = (String) JavaCalls.callMethod(telephonyManager, "getDeviceId", Integer.valueOf(i));
                } else if (1 == telephonyManager.getPhoneType()) {
                    str = (String) JavaCalls.callMethod(telephonyManager, "getImei", Integer.valueOf(i));
                } else if (2 == telephonyManager.getPhoneType()) {
                    str = (String) JavaCalls.callMethod(telephonyManager, "getMeid", Integer.valueOf(i));
                }
                if (!TextUtils.isEmpty(str) && !TextUtils.equals(sCachedIMEI, str) && verifyImei(str)) {
                    sCachedSubIMEIS += str + ",";
                }
            }
            int length = sCachedSubIMEIS.length();
            if (length > 0) {
                sCachedSubIMEIS = sCachedSubIMEIS.substring(0, length - 1);
            }
            return sCachedSubIMEIS;
        } catch (Exception e) {
            MyLog.e(e.toString());
            return "";
        }
    }

    public static String quicklyGetSubIMEISMd5(Context context) {
        quicklyGetSubIMEIS(context);
        if (TextUtils.isEmpty(sCachedSubIMEIS)) {
            return "";
        }
        String str = "";
        for (String str2 : sCachedSubIMEIS.split(",")) {
            if (verifyImei(str2)) {
                str = str + XMStringUtils.getMd5Digest(str2) + ",";
            }
        }
        int length = str.length();
        return length > 0 ? str.substring(0, length - 1) : str;
    }

    public static void updateVirtDevId(Context context, String str) {
        MyLog.v("update vdevid = " + str);
        if (!TextUtils.isEmpty(str)) {
            sVirtDevId = str;
            FileLocker fileLocker = null;
            try {
                if (isSupportVDevid(context)) {
                    File file = new File(Environment.getExternalStorageDirectory(), "/Xiaomi/");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                    File file2 = new File(file, ".vdevid");
                    fileLocker = FileLocker.lock(context, file2);
                    IOUtils.remove(file2);
                    IOUtils.strToFile(file2, sVirtDevId);
                }
                IOUtils.strToFile(new File(context.getFilesDir(), ".vdevid"), sVirtDevId);
                if (fileLocker == null) {
                    return;
                }
            } catch (IOException e) {
                MyLog.w("update vdevid failure :" + e.getMessage());
                if (fileLocker == null) {
                    return;
                }
            } catch (Throwable th) {
                if (fileLocker != null) {
                    fileLocker.unlock();
                }
                throw th;
            }
            fileLocker.unlock();
        }
    }

    private static boolean verifyImei(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 15 && str.length() >= 14 && XMStringUtils.isNumberAndLetter(str) && !XMStringUtils.isTheSameChars(str);
    }
}
