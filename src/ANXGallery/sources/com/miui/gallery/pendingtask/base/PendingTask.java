package com.miui.gallery.pendingtask.base;

public abstract class PendingTask<T> {
    private boolean isCancelled = false;
    private Callback mCallback;
    private int mType = -1;

    public interface Callback {
        boolean isCancelled();
    }

    public PendingTask(int i) {
        this.mType = i;
    }

    public long getMinLatency() {
        return 0;
    }

    public abstract int getNetworkType();

    public int getType() {
        return this.mType;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.mCallback;
     */
    public boolean isCancelled() {
        Callback callback;
        return this.isCancelled || (callback != null && callback.isCancelled());
    }

    public abstract T parseData(byte[] bArr) throws Exception;

    public abstract boolean process(T t) throws Exception;

    public abstract boolean requireCharging();

    public abstract boolean requireDeviceIdle();

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public abstract byte[] wrapData(T t) throws Exception;
}
