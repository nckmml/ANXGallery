package com.miui.gallery.sdk.download.executor;

import com.miui.gallery.sdk.download.assist.DownloadItem;
import com.miui.gallery.util.Log;

public class SingleThreadExecutor extends AbsDownloadExecutor {
    private boolean mPendingDispatch = false;
    private Object mWorkLock = new Object();
    private Thread mWorker;

    private class Job implements Runnable {
        private Job() {
        }

        private boolean needContinue() {
            return !Thread.currentThread().isInterrupted() && SingleThreadExecutor.this.mQueue.getPendingSize() > 0;
        }

        /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        private void runInner() {
            /*
                r8 = this;
            L_0x0000:
                boolean r0 = r8.needContinue()
                if (r0 == 0) goto L_0x00bd
                com.miui.gallery.sdk.download.executor.SingleThreadExecutor r0 = com.miui.gallery.sdk.download.executor.SingleThreadExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r0 = r0.mQueue
                java.util.List r0 = r0.pollToExecute()
                int r1 = r0.size()
                if (r1 <= 0) goto L_0x0000
                java.util.Map r0 = com.miui.gallery.sdk.download.executor.AbsDownloadExecutor.classifyCommand(r0)
                if (r0 == 0) goto L_0x0000
                java.util.LinkedList r1 = new java.util.LinkedList
                r1.<init>()
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L_0x0027:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L_0x0000
                java.lang.Object r2 = r0.next()
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                java.lang.Object r3 = r2.getValue()
                java.util.List r3 = (java.util.List) r3
                r1.clear()
                java.util.Iterator r4 = r3.iterator()
            L_0x0040:
                boolean r5 = r4.hasNext()
                if (r5 == 0) goto L_0x005a
                java.lang.Object r5 = r4.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r5 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r5
                boolean r6 = com.miui.gallery.sdk.download.executor.DownloadCommand.checkValid(r5)
                if (r6 == 0) goto L_0x0040
                com.miui.gallery.sdk.download.assist.DownloadItem r5 = r5.getItem()
                r1.add(r5)
                goto L_0x0040
            L_0x005a:
                com.miui.gallery.cloud.AccountCache$AccountInfo r4 = com.miui.gallery.cloud.AccountCache.getAccountInfo()
                java.lang.String r5 = "SingleThreadExecutor"
                if (r4 == 0) goto L_0x00b6
                int r6 = r1.size()
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.String r7 = "%s execute size %d"
                com.miui.gallery.util.Log.i(r5, r7, r8, r6)
                java.lang.Object r2 = r2.getKey()     // Catch:{ all -> 0x0098 }
                com.miui.gallery.sdk.download.downloader.IDownloader r2 = (com.miui.gallery.sdk.download.downloader.IDownloader) r2     // Catch:{ all -> 0x0098 }
                android.accounts.Account r5 = r4.mAccount     // Catch:{ all -> 0x0098 }
                com.miui.gallery.cloud.base.GalleryExtendedAuthToken r4 = r4.mToken     // Catch:{ all -> 0x0098 }
                r2.download(r5, r4, r1)     // Catch:{ all -> 0x0098 }
                java.util.Iterator r2 = r3.iterator()
            L_0x0080:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0027
                java.lang.Object r3 = r2.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r3 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r3
                com.miui.gallery.sdk.download.executor.SingleThreadExecutor r4 = com.miui.gallery.sdk.download.executor.SingleThreadExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r4 = r4.mQueue
                java.lang.String r3 = r3.getKey()
                r4.removeFromExecuting(r3)
                goto L_0x0080
            L_0x0098:
                r0 = move-exception
                java.util.Iterator r1 = r3.iterator()
            L_0x009d:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x00b5
                java.lang.Object r2 = r1.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r2 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r2
                com.miui.gallery.sdk.download.executor.SingleThreadExecutor r3 = com.miui.gallery.sdk.download.executor.SingleThreadExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r3 = r3.mQueue
                java.lang.String r2 = r2.getKey()
                r3.removeFromExecuting(r2)
                goto L_0x009d
            L_0x00b5:
                throw r0
            L_0x00b6:
                java.lang.String r2 = "execute: account is null"
                com.miui.gallery.util.Log.e((java.lang.String) r5, (java.lang.String) r2)
                goto L_0x0027
            L_0x00bd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.sdk.download.executor.SingleThreadExecutor.Job.runInner():void");
        }

        public void run() {
            try {
                runInner();
            } finally {
                SingleThreadExecutor.this.onTaskEnd();
            }
        }
    }

    public SingleThreadExecutor(int i, int i2) {
        super(i, i2);
    }

    private void ensureWork() {
        synchronized (this.mWorkLock) {
            if (this.mWorker == null) {
                this.mWorker = new Thread(new Job(), getTag());
                this.mWorker.setPriority(4);
                this.mWorker.start();
            } else if (this.mWorker.isInterrupted() || !this.mWorker.isAlive()) {
                this.mPendingDispatch = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onTaskEnd() {
        synchronized (this.mWorkLock) {
            if (this.mWorker != null) {
                this.mWorker = null;
            }
            if (this.mPendingDispatch && this.mQueue.getPendingSize() > 0) {
                Log.d("SingleThreadExecutor", "onTaskEnd need dispatch");
                this.mPendingDispatch = false;
                dispatch();
            }
        }
    }

    public boolean cancel(DownloadItem downloadItem) {
        int cancel = this.mQueue.cancel(downloadItem.getKey());
        if (cancel == 1) {
            interrupt();
        }
        return cancel != -1;
    }

    /* access modifiers changed from: protected */
    public void dispatch() {
        ensureWork();
    }

    /* access modifiers changed from: protected */
    public String getTag() {
        return "SingleThreadExecutor";
    }

    public void interrupt() {
        synchronized (this.mWorkLock) {
            if (this.mWorker != null) {
                this.mWorker.interrupt();
            }
        }
        this.mQueue.interrupt();
    }
}
