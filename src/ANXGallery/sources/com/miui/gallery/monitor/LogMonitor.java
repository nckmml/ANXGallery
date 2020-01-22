package com.miui.gallery.monitor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

class LogMonitor {
    private final long mBlockTime;
    private long mStart;
    private final long mTraceStackTime = ((long) (((float) this.mBlockTime) * 0.8f));
    private final Handler mWorkHandler;

    private static class WorkHandler extends Handler {
        private final FileLog mFileLog = new FileLog();
        private final Looper mMonitorLooper;
        private String mStackTrace;

        public WorkHandler(Looper looper, Looper looper2) {
            super(looper);
            this.mMonitorLooper = looper2;
        }

        private void printLog(String str) {
            String str2 = this.mStackTrace;
            if (str2 != null) {
                Log.w("LOOPER_MONITOR", str2);
            }
            if (str != null) {
                Log.w("LOOPER_MONITOR", str);
            }
            this.mFileLog.log(this.mStackTrace + str + "\n------------------------------------\n");
        }

        private void traceStack() {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : this.mMonitorLooper.getThread().getStackTrace()) {
                sb.append(stackTraceElement.toString());
                sb.append("\n");
            }
            this.mStackTrace = sb.toString();
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                traceStack();
            } else if (i == 2) {
                printLog((String) message.obj);
            }
            super.handleMessage(message);
        }
    }

    LogMonitor(Looper looper, long j) {
        this.mBlockTime = Math.max(30, Math.min(j, 3000));
        HandlerThread handlerThread = new HandlerThread("monitor work", 10);
        handlerThread.start();
        this.mWorkHandler = new WorkHandler(handlerThread.getLooper(), looper);
    }

    /* access modifiers changed from: package-private */
    public void startMonitor(String str) {
        this.mStart = SystemClock.uptimeMillis();
        this.mWorkHandler.sendEmptyMessageDelayed(1, this.mTraceStackTime);
    }

    /* access modifiers changed from: package-private */
    public void stopMonitor(String str) {
        this.mWorkHandler.removeMessages(1);
        if (this.mStart > 0) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.mStart;
            if (uptimeMillis > this.mBlockTime && !TextUtils.isEmpty(str)) {
                this.mWorkHandler.obtainMessage(2, String.format("%s ########### Processing: %sms", new Object[]{str, Long.valueOf(uptimeMillis)})).sendToTarget();
            }
        }
    }
}
