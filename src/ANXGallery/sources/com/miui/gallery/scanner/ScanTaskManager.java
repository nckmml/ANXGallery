package com.miui.gallery.scanner;

import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ScanTaskManager implements FutureListener {
    private int mLimit;
    private OnAllTasksExecutedListener mListener;
    private Object mLock;
    private ThreadPool mPool;
    private LinkedList<Future> mRunningQueue;
    private PriorityQueue<ScanTask> mWaitQueue;

    public interface OnAllTasksExecutedListener {
        void onAllTasksExecuted();
    }

    public ScanTaskManager() {
        this.mLock = new Object();
        this.mWaitQueue = new PriorityQueue<>();
        this.mRunningQueue = new LinkedList<>();
        this.mPool = new ThreadPool(1, 1);
        this.mLimit = 1;
    }

    public ScanTaskManager(int i, OnAllTasksExecutedListener onAllTasksExecutedListener) {
        this.mLock = new Object();
        this.mWaitQueue = new PriorityQueue<>();
        this.mRunningQueue = new LinkedList<>();
        this.mListener = onAllTasksExecutedListener;
        this.mPool = new ThreadPool(i, i);
        this.mLimit = i;
    }

    private boolean contains(ScanTask scanTask) {
        if (!scanTask.isForceScan()) {
            Iterator it = this.mRunningQueue.iterator();
            while (it.hasNext()) {
                Future future = (Future) it.next();
                if (!future.isCancelled() && scanTask.equals((ScanTask) future.getJob())) {
                    return true;
                }
            }
        }
        return this.mWaitQueue.contains(scanTask);
    }

    private void submitIfAllowed() {
        if (this.mPool.isShutdown()) {
            cancelAll();
            return;
        }
        while (this.mLimit > 0 && !this.mWaitQueue.isEmpty()) {
            ScanTask poll = this.mWaitQueue.poll();
            this.mLimit--;
            Log.i("ScanTaskManager", "priority %d, time %d", Integer.valueOf(poll.getPriority()), Long.valueOf(poll.getNewTime()));
            this.mRunningQueue.add(this.mPool.submit(poll, this));
        }
    }

    public void cancelAll() {
        synchronized (this.mLock) {
            this.mWaitQueue.clear();
            Iterator it = this.mRunningQueue.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel();
            }
        }
    }

    public void cancelByPriority(int i) {
        synchronized (this.mLock) {
            Iterator<ScanTask> it = this.mWaitQueue.iterator();
            while (it.hasNext()) {
                ScanTask next = it.next();
                if (i == next.getPriority()) {
                    it.remove();
                    Log.d("ScanTaskManager", "cancel task [%s] by priority [%d]", next.toString(), Integer.valueOf(i));
                }
            }
            Iterator it2 = this.mRunningQueue.iterator();
            while (it2.hasNext()) {
                Future future = (Future) it2.next();
                ScanTask scanTask = (ScanTask) future.getJob();
                if (i == scanTask.getPriority()) {
                    future.cancel();
                    Log.d("ScanTaskManager", "cancel task [%s] by priority [%d]", scanTask.toString(), Integer.valueOf(i));
                }
            }
        }
    }

    public void cancelForegroundTask() {
        synchronized (this.mLock) {
            Iterator<ScanTask> it = this.mWaitQueue.iterator();
            while (it.hasNext()) {
                ScanTask next = it.next();
                if (next.isForeground() && !next.canRunningInBackground()) {
                    it.remove();
                    Log.i("ScanTaskManager", "cancel foreground task %s", (Object) next.toString());
                }
            }
            Iterator it2 = this.mRunningQueue.iterator();
            while (it2.hasNext()) {
                Future future = (Future) it2.next();
                ScanTask scanTask = (ScanTask) future.getJob();
                if (scanTask.isForeground() && !scanTask.canRunningInBackground()) {
                    future.cancel();
                    Log.i("ScanTaskManager", "cancel foreground task %s", (Object) scanTask.toString());
                }
            }
        }
    }

    public boolean isEmpty() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mRunningQueue.isEmpty() && this.mWaitQueue.isEmpty();
        }
        return z;
    }

    public void onFutureDone(Future future) {
        ScanTask scanTask;
        synchronized (this.mLock) {
            if (future != null) {
                if (future.getCancelType() == 1 && (scanTask = (ScanTask) future.getJob()) != null) {
                    Log.i("ScanTaskManager", "CANCEL_INTERRUPT %d", (Object) Integer.valueOf(scanTask.getPriority()));
                    this.mWaitQueue.offer(scanTask);
                }
            }
            this.mRunningQueue.remove(future);
            if (this.mListener != null && isEmpty()) {
                this.mListener.onAllTasksExecuted();
            }
            this.mLimit++;
            submitIfAllowed();
        }
    }

    public void shutdown() {
        cancelAll();
        this.mPool.shutdown();
    }

    public void submit(ScanTask scanTask) {
        if (scanTask != null) {
            synchronized (this.mLock) {
                if (contains(scanTask)) {
                    Log.i("ScanTaskManager", "contains task %s", (Object) scanTask.toString());
                    return;
                }
                this.mWaitQueue.offer(scanTask);
                Iterator it = this.mRunningQueue.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    ScanTask scanTask2 = (ScanTask) future.getJob();
                    if (scanTask2 != null && scanTask2.compareTo(scanTask) > 0) {
                        Log.i("ScanTaskManager", "priority %d is interrupted by %d", Integer.valueOf(scanTask2.getPriority()), Integer.valueOf(scanTask.getPriority()));
                        future.cancel(1);
                    }
                }
                submitIfAllowed();
            }
        }
    }
}
