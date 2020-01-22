package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.push.log.MIPushDebugLog;
import com.xiaomi.push.log.MIPushLog2File;
import java.io.File;
import java.util.HashMap;

public class Logger {
    private static boolean sDisablePushLog;
    private static LoggerInterface sUserLogger;

    public static File getLogFile(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isFile() && !listFiles[i].getName().contains("lock") && listFiles[i].getName().contains("log")) {
                            return listFiles[i];
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (NullPointerException unused) {
            MyLog.e("null pointer exception while retrieve file.");
        }
    }

    protected static LoggerInterface getUserLogger() {
        return sUserLogger;
    }

    private static boolean hasWritePermission(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String equals : strArr) {
                    if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(equals)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static void setLogger(Context context, LoggerInterface loggerInterface) {
        sUserLogger = loggerInterface;
        setPushLog(context);
    }

    public static void setPushLog(Context context) {
        boolean z = false;
        boolean z2 = sUserLogger != null;
        if (sDisablePushLog) {
            z2 = false;
        } else if (hasWritePermission(context)) {
            z = true;
        }
        MIPushLog2File mIPushLog2File = null;
        LoggerInterface loggerInterface = z2 ? sUserLogger : null;
        if (z) {
            mIPushLog2File = new MIPushLog2File(context);
        }
        MyLog.setLogger(new MIPushDebugLog(loggerInterface, mIPushLog2File));
    }

    public static void uploadLogFile(final Context context, final boolean z) {
        ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:27:0x009b  */
            /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
            public void run() {
                File file;
                String str;
                try {
                    HashMap<String, String> collectDeviceInfo = MiPushUtils.collectDeviceInfo(context, "");
                    if (z) {
                        str = context.getFilesDir().getAbsolutePath();
                    } else {
                        str = context.getExternalFilesDir((String) null).getAbsolutePath() + MIPushLog2File.MIPUSH_LOG_PATH;
                    }
                    File logFile = Logger.getLogFile(str);
                    if (logFile == null) {
                        MyLog.w("log file null");
                        return;
                    }
                    file = new File(str, context.getPackageName() + ".zip");
                    try {
                        IOUtils.zip(file, logFile);
                        if (file.exists()) {
                            Network.uploadFile((z ? "https://api.xmpush.xiaomi.com/upload/xmsf_log?file=" : "https://api.xmpush.xiaomi.com/upload/app_log?file=") + file.getName(), collectDeviceInfo, file, "file");
                        } else {
                            MyLog.w("zip log file failed");
                        }
                    } catch (Throwable th) {
                        th = th;
                        MyLog.e(th);
                        if (file != null || file.exists()) {
                        }
                    }
                    if (file != null && file.exists()) {
                        file.delete();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    file = null;
                    MyLog.e(th);
                    if (file != null || file.exists()) {
                    }
                }
            }
        });
    }
}
