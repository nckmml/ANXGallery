package miui.cloud.common;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Locale;

public class XLogger {
    private static final String CLASS_TAG = "miui.cloud.common.XLogger";
    private static volatile int sDefLogLevel = 4;
    private static volatile boolean sEnableCallStacktrace = true;
    private static volatile LogSender sLogSender = new LogSender() {
        private final boolean sLogOnAdb = new File(Environment.getExternalStorageDirectory(), "log_on_adb").exists();

        public void sendLog(int i, String str, String str2) {
            if (this.sLogOnAdb) {
                Log.println(i, str, str2);
            }
        }
    };
    private static volatile int sOutputLogLevel = 2;

    public interface LogSender {
        void sendLog(int i, String str, String str2);
    }

    public static void error(String str, String str2, Object... objArr) {
        logAtLevelImp(6, str, formatStr(str2, objArr));
    }

    private static String formatStr(String str, Object... objArr) {
        try {
            String format = String.format(Locale.ENGLISH, str, objArr);
            if (objArr == null || objArr.length == 0) {
                return format;
            }
            Throwable th = objArr[objArr.length - 1];
            if (!(th instanceof Throwable)) {
                return format;
            }
            return format + getThrowableString(th);
        } catch (IllegalFormatException e) {
            return str + " " + Arrays.toString(objArr) + " : " + getThrowableString(e);
        }
    }

    private static String getCallerInfo() {
        boolean z = false;
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (stackTraceElement.getClassName().equals(CLASS_TAG)) {
                z = true;
            } else if (z) {
                return String.format("%s::%s@%s:%s, thread:%s", new Object[]{stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getFileName(), Integer.valueOf(stackTraceElement.getLineNumber()), Long.valueOf(Thread.currentThread().getId())});
            }
        }
        return "";
    }

    private static String getObjectString(Object obj) {
        return obj == null ? "NULL" : obj instanceof Throwable ? getThrowableString((Throwable) obj) : obj.toString();
    }

    private static String getThrowableString(Throwable th) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10 && th != null; i++) {
            if (sb.length() != 0) {
                sb.append(" *Caused by* ");
            }
            sb.append(th.toString());
            th = th.getCause();
        }
        if (th != null) {
            sb.append(" *and more...*");
        }
        return sb.toString();
    }

    public static void info(String str, String str2, Object... objArr) {
        logAtLevelImp(4, str, formatStr(str2, objArr));
    }

    private static void logAtLevelImp(int i, String str, Object... objArr) {
        LogSender logSender;
        String str2;
        Object obj;
        if (i >= sOutputLogLevel && (logSender = sLogSender) != null) {
            if (sEnableCallStacktrace) {
                str2 = getCallerInfo() + "--";
            } else {
                str2 = "";
            }
            if (objArr.length == 1) {
                obj = getObjectString(objArr[0]);
            } else {
                StringBuilder sb = new StringBuilder();
                for (Object obj2 : objArr) {
                    if (sb.length() != 0) {
                        sb.append(", ");
                    }
                    sb.append(getObjectString(obj2));
                }
                obj = sb;
            }
            if (str == null) {
                str = "##XLogger##";
            }
            logSender.sendLog(i, str, str2 + obj);
        }
    }
}
