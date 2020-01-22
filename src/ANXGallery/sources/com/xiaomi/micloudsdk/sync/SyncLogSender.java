package com.xiaomi.micloudsdk.sync;

abstract class SyncLogSender {
    SyncLogSender() {
    }

    /* access modifiers changed from: package-private */
    public abstract void openSyncLog();

    /* access modifiers changed from: package-private */
    public abstract void release();

    /* access modifiers changed from: package-private */
    public abstract void sendLog(String str, String str2);
}
