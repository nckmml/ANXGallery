package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;

public class MIPushDebugLog implements LoggerInterface {
    private LoggerInterface sPushLogFileInterface = null;
    private LoggerInterface sUserLogInterface = null;

    public MIPushDebugLog(LoggerInterface loggerInterface, LoggerInterface loggerInterface2) {
        this.sUserLogInterface = loggerInterface;
        this.sPushLogFileInterface = loggerInterface2;
    }

    public void log(String str) {
        LoggerInterface loggerInterface = this.sUserLogInterface;
        if (loggerInterface != null) {
            loggerInterface.log(str);
        }
        LoggerInterface loggerInterface2 = this.sPushLogFileInterface;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str);
        }
    }

    public void log(String str, Throwable th) {
        LoggerInterface loggerInterface = this.sUserLogInterface;
        if (loggerInterface != null) {
            loggerInterface.log(str, th);
        }
        LoggerInterface loggerInterface2 = this.sPushLogFileInterface;
        if (loggerInterface2 != null) {
            loggerInterface2.log(str, th);
        }
    }
}
