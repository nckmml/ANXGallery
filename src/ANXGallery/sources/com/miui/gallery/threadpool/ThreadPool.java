package com.miui.gallery.threadpool;

import com.miui.gallery.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static final JobContext JOB_CONTEXT_STUB = new JobContextStub();
    ResourceCounter mCpuCounter;
    private final ThreadPoolExecutor mExecutor;
    ResourceCounter mNetworkCounter;

    public interface CancelListener {
        void onCancel();
    }

    public interface Job<T> {
        T run(JobContext jobContext);
    }

    public interface JobContext {
        boolean isCancelled();

        void setCancelListener(CancelListener cancelListener);
    }

    private static class JobContextStub implements JobContext {
        private JobContextStub() {
        }

        public boolean isCancelled() {
            return false;
        }

        public void setCancelListener(CancelListener cancelListener) {
        }
    }

    private static class ResourceCounter {
        public int value;

        public ResourceCounter(int i) {
            this.value = i;
        }
    }

    private class Worker<T> implements Future<T>, JobContext, Runnable {
        private CancelListener mCancelListener;
        private int mCancelType = 0;
        private volatile boolean mIsCancelled;
        private boolean mIsDone;
        private Job<T> mJob;
        private FutureListener<T> mListener;
        private int mMode;
        private T mResult;
        private ResourceCounter mWaitOnResource;

        public Worker(Job<T> job, FutureListener<T> futureListener) {
            this.mJob = job;
            this.mListener = futureListener;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:25|26|27) */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0011, code lost:
            if (r4.value <= 0) goto L_0x0022;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0013, code lost:
            r4.value--;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0019, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
            monitor-enter(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r3.mWaitOnResource = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x001d, code lost:
            monitor-exit(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x001e, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            r4.wait();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0025 */
        private boolean acquireResource(ResourceCounter resourceCounter) {
            while (true) {
                synchronized (this) {
                    if (this.mIsCancelled) {
                        this.mWaitOnResource = null;
                        return false;
                    }
                    this.mWaitOnResource = resourceCounter;
                }
            }
            while (true) {
            }
        }

        private ResourceCounter modeToCounter(int i) {
            if (i == 1) {
                return ThreadPool.this.mCpuCounter;
            }
            if (i == 2) {
                return ThreadPool.this.mNetworkCounter;
            }
            return null;
        }

        private void releaseResource(ResourceCounter resourceCounter) {
            synchronized (resourceCounter) {
                resourceCounter.value++;
                resourceCounter.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0025, code lost:
            return;
         */
        public synchronized void cancel() {
            if (!this.mIsCancelled) {
                this.mIsCancelled = true;
                if (this.mWaitOnResource != null) {
                    synchronized (this.mWaitOnResource) {
                        this.mWaitOnResource.notifyAll();
                    }
                }
                if (this.mCancelListener != null) {
                    this.mCancelListener.onCancel();
                }
            }
        }

        public synchronized void cancel(int i) {
            this.mCancelType = i;
            cancel();
        }

        public synchronized T get() {
            while (!this.mIsDone) {
                try {
                    wait();
                } catch (Exception e) {
                    Log.w("Worker", "ingore exception", e);
                }
            }
            return this.mResult;
        }

        public int getCancelType() {
            return this.mCancelType;
        }

        public Job<T> getJob() {
            return this.mJob;
        }

        public boolean isCancelled() {
            return this.mIsCancelled;
        }

        public synchronized boolean isDone() {
            return this.mIsDone;
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:657)
            	at java.util.ArrayList.get(ArrayList.java:433)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
            */
        public void run() {
            /*
                r4 = this;
                r0 = 1
                boolean r1 = r4.setMode(r0)
                if (r1 == 0) goto L_0x0016
                com.miui.gallery.threadpool.ThreadPool$Job<T> r1 = r4.mJob     // Catch:{ Throwable -> 0x000e }
                java.lang.Object r1 = r1.run(r4)     // Catch:{ Throwable -> 0x000e }
                goto L_0x0017
            L_0x000e:
                r1 = move-exception
                java.lang.String r2 = "Worker"
                java.lang.String r3 = "Exception in running a job"
                com.miui.gallery.util.Log.w(r2, r3, r1)
            L_0x0016:
                r1 = 0
            L_0x0017:
                monitor-enter(r4)
                r2 = 0
                r4.setMode(r2)     // Catch:{ all -> 0x002c }
                r4.mResult = r1     // Catch:{ all -> 0x002c }
                r4.mIsDone = r0     // Catch:{ all -> 0x002c }
                r4.notifyAll()     // Catch:{ all -> 0x002c }
                monitor-exit(r4)     // Catch:{ all -> 0x002c }
                com.miui.gallery.threadpool.FutureListener<T> r0 = r4.mListener
                if (r0 == 0) goto L_0x002b
                r0.onFutureDone(r4)
            L_0x002b:
                return
            L_0x002c:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x002c }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.gallery.threadpool.ThreadPool.Worker.run():void");
        }

        public synchronized void setCancelListener(CancelListener cancelListener) {
            this.mCancelListener = cancelListener;
            if (this.mIsCancelled && this.mCancelListener != null) {
                this.mCancelListener.onCancel();
            }
        }

        public boolean setMode(int i) {
            ResourceCounter modeToCounter = modeToCounter(this.mMode);
            if (modeToCounter != null) {
                releaseResource(modeToCounter);
            }
            this.mMode = 0;
            ResourceCounter modeToCounter2 = modeToCounter(i);
            if (modeToCounter2 == null) {
                return true;
            }
            if (!acquireResource(modeToCounter2)) {
                return false;
            }
            this.mMode = i;
            return true;
        }
    }

    public ThreadPool() {
        this(4, 8);
    }

    public ThreadPool(int i, int i2) {
        this.mCpuCounter = new ResourceCounter(4);
        this.mNetworkCounter = new ResourceCounter(2);
        this.mExecutor = new ThreadPoolExecutor(i, i2, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory("thread-pool", 10));
    }

    public ThreadPool(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        this.mCpuCounter = new ResourceCounter(4);
        this.mNetworkCounter = new ResourceCounter(2);
        this.mExecutor = new ThreadPoolExecutor(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    public ThreadPool(int i, int i2, ThreadFactory threadFactory, int i3, RejectedExecutionHandler rejectedExecutionHandler) {
        this.mCpuCounter = new ResourceCounter(4);
        this.mNetworkCounter = new ResourceCounter(2);
        this.mExecutor = new ThreadPoolExecutor(i, i2, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(i3), threadFactory, rejectedExecutionHandler);
    }

    public ExecutorService asExecutorService() {
        return this.mExecutor;
    }

    public boolean isShutdown() {
        return this.mExecutor.isShutdown();
    }

    public void shutdown() {
        try {
            this.mExecutor.shutdown();
        } catch (Throwable th) {
            Log.w("ThreadPool", th);
        }
    }

    public void shutdownNow() {
        try {
            this.mExecutor.shutdownNow();
        } catch (Throwable th) {
            Log.w("ThreadPool", th);
        }
    }

    public <T> Future<T> submit(Job<T> job) {
        return submit(job, (FutureListener) null);
    }

    public <T> Future<T> submit(Job<T> job, FutureListener<T> futureListener) {
        Worker worker = new Worker(job, futureListener);
        this.mExecutor.execute(worker);
        return worker;
    }
}
