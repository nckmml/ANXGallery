package com.xiaomi.channel.commonutils.logger;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog {
    private static int LOG_LEVEL = 2;
    private static final Integer NEGATIVE_CODE = -1;
    private static LoggerInterface logger = new DefaultAndroidLogger();
    private static final HashMap<Integer, String> mActionNames = new HashMap<>();
    private static AtomicInteger mCodeGenerator = new AtomicInteger(1);
    private static final HashMap<Integer, Long> mStartTimes = new HashMap<>();

    public static void e(String str) {
        log(4, str);
    }

    public static void e(String str, Throwable th) {
        log(4, str, th);
    }

    public static void e(Throwable th) {
        log(4, th);
    }

    public static int getLogLevel() {
        return LOG_LEVEL;
    }

    public static void i(String str) {
        log(0, str);
    }

    public static void log(int i, String str) {
        if (i >= LOG_LEVEL) {
            logger.log(str);
        }
    }

    public static void log(int i, String str, Throwable th) {
        if (i >= LOG_LEVEL) {
            logger.log(str, th);
        }
    }

    public static void log(int i, Throwable th) {
        if (i >= LOG_LEVEL) {
            logger.log("", th);
        }
    }

    public static void pe(Integer num) {
        if (LOG_LEVEL <= 1 && mStartTimes.containsKey(num)) {
            long currentTimeMillis = System.currentTimeMillis() - mStartTimes.remove(num).longValue();
            LoggerInterface loggerInterface = logger;
            loggerInterface.log(mActionNames.remove(num) + " ends in " + currentTimeMillis + " ms");
        }
    }

    public static Integer ps(String str) {
        if (LOG_LEVEL > 1) {
            return NEGATIVE_CODE;
        }
        Integer valueOf = Integer.valueOf(mCodeGenerator.incrementAndGet());
        mStartTimes.put(valueOf, Long.valueOf(System.currentTimeMillis()));
        mActionNames.put(valueOf, str);
        LoggerInterface loggerInterface = logger;
        loggerInterface.log(str + " starts");
        return valueOf;
    }

    public static void setLogLevel(int i) {
        if (i < 0 || i > 5) {
            log(2, "set log level as " + i);
        }
        LOG_LEVEL = i;
    }

    public static void setLogger(LoggerInterface loggerInterface) {
        logger = loggerInterface;
    }

    public static void v(String str) {
        log(1, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }

    public static void w(String str) {
        log(2, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }
}
