package com.xiaomi.push.service.timers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMJobService;

public final class Alarm {
    private static final String XMSERVICE = XMJobService.class.getCanonicalName();
    private static IAlarm sAlarmInstance;
    private static int sLevel = 0;

    interface IAlarm {
        boolean isAlive();

        void registerPing(boolean z);

        void stop();
    }

    public static synchronized void changePolicy(Context context, int i) {
        synchronized (Alarm.class) {
            int i2 = sLevel;
            if (!"com.xiaomi.xmsf".equals(context.getPackageName())) {
                if (i == 2) {
                    sLevel = 2;
                } else {
                    sLevel = 0;
                }
            }
            if (i2 != sLevel && sLevel == 2) {
                stop();
                sAlarmInstance = new HybridTimer(context);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
        if (XMSERVICE.equals(com.xiaomi.channel.commonutils.android.SystemUtils.loadClass(r9, r6.name).getSuperclass().getCanonicalName()) != false) goto L_0x0048;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c8  */
    public static void initialize(Context context) {
        boolean z;
        Context applicationContext = context.getApplicationContext();
        if ("com.xiaomi.xmsf".equals(applicationContext.getPackageName())) {
            sAlarmInstance = new AlarmManagerTimer(applicationContext);
            return;
        }
        int i = 0;
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4);
            if (packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                int length = serviceInfoArr.length;
                z = false;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    try {
                        ServiceInfo serviceInfo = serviceInfoArr[i];
                        if ("android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                            if (!XMSERVICE.equals(serviceInfo.name)) {
                                try {
                                } catch (Exception unused) {
                                }
                            }
                            z = true;
                            if (z) {
                                break;
                            }
                        }
                        if (XMSERVICE.equals(serviceInfo.name) && "android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                            z = true;
                            break;
                        }
                        i++;
                    } catch (Exception e) {
                        e = e;
                        MyLog.w("check service err : " + e.getMessage());
                        if (z || !SystemUtils.isDebuggable(applicationContext)) {
                        }
                    }
                }
            } else {
                z = false;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
            MyLog.w("check service err : " + e.getMessage());
            if (z || !SystemUtils.isDebuggable(applicationContext)) {
            }
        }
        if (z || !SystemUtils.isDebuggable(applicationContext)) {
            int i2 = Build.VERSION.SDK_INT;
            sAlarmInstance = new AlarmManagerTimer(applicationContext);
            return;
        }
        throw new RuntimeException("Should export service: " + XMSERVICE + " with permission " + "android.permission.BIND_JOB_SERVICE" + " in AndroidManifest.xml file");
    }

    public static synchronized boolean isAlive() {
        synchronized (Alarm.class) {
            if (sAlarmInstance == null) {
                return false;
            }
            boolean isAlive = sAlarmInstance.isAlive();
            return isAlive;
        }
    }

    public static synchronized void registerPing(boolean z) {
        synchronized (Alarm.class) {
            if (sAlarmInstance == null) {
                MyLog.w("timer is not initialized");
                return;
            }
            MyLog.v("register alarm. (" + z + ")");
            sAlarmInstance.registerPing(z);
        }
    }

    public static synchronized void stop() {
        synchronized (Alarm.class) {
            if (sAlarmInstance != null) {
                MyLog.v("stop alarm.");
                sAlarmInstance.stop();
            }
        }
    }
}
