package com.miui.gallery.sdk.download.executor;

import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;

public class ThreadPoolExecutor extends AbsDownloadExecutor implements FutureListener {
    private final int mCoreSize;
    private Object mExecutorLock;
    private Future[] mFutures;
    private ThreadPool mPool;

    private class Job implements ThreadPool.Job {
        private Job() {
        }

        private boolean needContinue(ThreadPool.JobContext jobContext) {
            Log.i("ThreadPoolExecutor", "pendingSize %d, interrupted %s, canceled %s", Integer.valueOf(ThreadPoolExecutor.this.mQueue.getPendingSize()), Boolean.valueOf(Thread.currentThread().isInterrupted()), Boolean.valueOf(jobContext.isCancelled()));
            return ThreadPoolExecutor.this.mQueue.getPendingSize() > 0 && !Thread.currentThread().isInterrupted() && !jobContext.isCancelled();
        }

        /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
            jadx.core.utils.exceptions.JadxOverflowException: 
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        public java.lang.Object run(com.miui.gallery.threadpool.ThreadPool.JobContext r9) {
            /*
                r8 = this;
            L_0x0000:
                boolean r0 = r8.needContinue(r9)
                java.lang.String r1 = "ThreadPoolExecutor"
                if (r0 == 0) goto L_0x00bd
                com.miui.gallery.sdk.download.executor.ThreadPoolExecutor r0 = com.miui.gallery.sdk.download.executor.ThreadPoolExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r0 = r0.mQueue
                java.util.List r0 = r0.pollToExecute()
                int r2 = r0.size()
                if (r2 <= 0) goto L_0x0000
                java.util.Map r0 = com.miui.gallery.sdk.download.executor.AbsDownloadExecutor.classifyCommand(r0)
                if (r0 == 0) goto L_0x0000
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
            L_0x0029:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x0000
                java.lang.Object r3 = r0.next()
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r4 = r3.getValue()
                java.util.List r4 = (java.util.List) r4
                r2.clear()
                java.util.Iterator r5 = r4.iterator()
            L_0x0042:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x005c
                java.lang.Object r6 = r5.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r6 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r6
                boolean r7 = com.miui.gallery.sdk.download.executor.DownloadCommand.checkValid(r6)
                if (r7 == 0) goto L_0x0042
                com.miui.gallery.sdk.download.assist.DownloadItem r6 = r6.getItem()
                r2.add(r6)
                goto L_0x0042
            L_0x005c:
                com.miui.gallery.cloud.AccountCache$AccountInfo r5 = com.miui.gallery.cloud.AccountCache.getAccountInfo()
                if (r5 == 0) goto L_0x00b6
                int r6 = r2.size()
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.String r7 = "%s execute size %d"
                com.miui.gallery.util.Log.i(r1, r7, r8, r6)
                java.lang.Object r3 = r3.getKey()     // Catch:{ all -> 0x0098 }
                com.miui.gallery.sdk.download.downloader.IDownloader r3 = (com.miui.gallery.sdk.download.downloader.IDownloader) r3     // Catch:{ all -> 0x0098 }
                android.accounts.Account r6 = r5.mAccount     // Catch:{ all -> 0x0098 }
                com.miui.gallery.cloud.base.GalleryExtendedAuthToken r5 = r5.mToken     // Catch:{ all -> 0x0098 }
                r3.download(r6, r5, r2)     // Catch:{ all -> 0x0098 }
                java.util.Iterator r3 = r4.iterator()
            L_0x0080:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x0029
                java.lang.Object r4 = r3.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r4 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r4
                com.miui.gallery.sdk.download.executor.ThreadPoolExecutor r5 = com.miui.gallery.sdk.download.executor.ThreadPoolExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r5 = r5.mQueue
                java.lang.String r4 = r4.getKey()
                r5.removeFromExecuting(r4)
                goto L_0x0080
            L_0x0098:
                r9 = move-exception
                java.util.Iterator r0 = r4.iterator()
            L_0x009d:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x00b5
                java.lang.Object r1 = r0.next()
                com.miui.gallery.sdk.download.executor.DownloadCommand r1 = (com.miui.gallery.sdk.download.executor.DownloadCommand) r1
                com.miui.gallery.sdk.download.executor.ThreadPoolExecutor r2 = com.miui.gallery.sdk.download.executor.ThreadPoolExecutor.this
                com.miui.gallery.sdk.download.executor.DownloadCommandQueue r2 = r2.mQueue
                java.lang.String r1 = r1.getKey()
                r2.removeFromExecuting(r1)
                goto L_0x009d
            L_0x00b5:
                throw r9
            L_0x00b6:
                java.lang.String r3 = "execute: account is null"
                com.miui.gallery.util.Log.e((java.lang.String) r1, (java.lang.String) r3)
                goto L_0x0029
            L_0x00bd:
                java.lang.String r9 = "runnable end %s"
                com.miui.gallery.util.Log.d((java.lang.String) r1, (java.lang.String) r9, (java.lang.Object) r8)
                r9 = 0
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.sdk.download.executor.ThreadPoolExecutor.Job.run(com.miui.gallery.threadpool.ThreadPool$JobContext):java.lang.Object");
        }
    }

    public ThreadPoolExecutor(int i, int i2) {
        this(2, i, i2);
    }

    public ThreadPoolExecutor(int i, int i2, int i3) {
        super(i2, i3);
        this.mExecutorLock = new Object();
        this.mCoreSize = i;
        this.mFutures = new Future[this.mCoreSize];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2, types: [int] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        return false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    private boolean dispatchJobs() {
        synchronized (this.mExecutorLock) {
            ? r2 = 0;
            if (this.mPool != null) {
                if (!this.mPool.isShutdown()) {
                    int pendingSize = this.mQueue.getPendingSize();
                    if (pendingSize > 0) {
                        boolean z = false;
                        while (r2 < this.mCoreSize && pendingSize > 0) {
                            Future future = this.mFutures[r2];
                            if (future == null || future.isCancelled() || future.isDone()) {
                                Job job = new Job();
                                Log.i("ThreadPoolExecutor", "submit runnable %s", (Object) job);
                                this.mFutures[r2] = this.mPool.submit(job, this);
                                pendingSize -= this.mQueue.getBatchSize();
                                z = true;
                            }
                            r2++;
                        }
                        r2 = z;
                    }
                }
            }
        }
    }

    private void initExecutorIfNeed() {
        synchronized (this.mExecutorLock) {
            if (this.mPool == null || this.mPool.isShutdown()) {
                this.mPool = new ThreadPool(this.mCoreSize, this.mCoreSize);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatch() {
        synchronized (this.mExecutorLock) {
            initExecutorIfNeed();
            dispatchJobs();
        }
    }

    /* access modifiers changed from: protected */
    public String getTag() {
        return "ThreadPoolExecutor";
    }

    public void interrupt() {
        synchronized (this.mExecutorLock) {
            int i = 0;
            while (i < this.mCoreSize) {
                try {
                    if (this.mFutures[i] != null) {
                        this.mFutures[i].cancel(0);
                        this.mFutures[i] = null;
                    }
                    i++;
                } catch (Exception e) {
                    Log.e("ThreadPoolExecutor", (Throwable) e);
                }
            }
            if (this.mPool != null) {
                this.mPool.shutdownNow();
            }
        }
        this.mQueue.interrupt();
    }

    public void onFutureDone(Future future) {
        if (!future.isCancelled()) {
            Log.i("ThreadPoolExecutor", "onFutureDone dispatch %s", (Object) Boolean.valueOf(dispatchJobs()));
        }
    }
}
