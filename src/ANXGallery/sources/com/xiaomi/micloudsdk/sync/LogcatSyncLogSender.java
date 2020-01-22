package com.xiaomi.micloudsdk.sync;

import android.util.Log;

class LogcatSyncLogSender extends SyncLogSender {
    LogcatSyncLogSender() {
    }

    /* access modifiers changed from: package-private */
    public void openSyncLog() {
    }

    /* access modifiers changed from: package-private */
    public void release() {
    }

    /* access modifiers changed from: package-private */
    public void sendLog(String str, String str2) {
        Log.i(str, str2);
    }
}
