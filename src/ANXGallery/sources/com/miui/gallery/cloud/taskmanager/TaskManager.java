package com.miui.gallery.cloud.taskmanager;

import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TaskManager implements FutureListener<Task> {
    private int mLimit;
    private Object mLock = new Object();
    private ThreadPool mPool;
    private LinkedList<Future> mRunningQueue = new LinkedList<>();
    private PriorityQueue<Task> mWaitQueue = new PriorityQueue<>();

    public TaskManager(int i) {
        this.mLimit = i;
        int i2 = this.mLimit;
        this.mPool = new ThreadPool(i2, i2);
    }

    private boolean contains(Task task) {
        Iterator it = this.mRunningQueue.iterator();
        while (it.hasNext()) {
            Future future = (Future) it.next();
            if (!future.isCancelled() && task.equals((Task) future.getJob())) {
                return true;
            }
        }
        return this.mWaitQueue.contains(task);
    }

    private boolean needReschedule(Future future) {
        return future != null && future.getCancelType() == 1;
    }

    private void submitIfAllowed() {
        if (this.mPool.isShutdown()) {
            cancelAll();
            return;
        }
        while (this.mLimit > 0 && !this.mWaitQueue.isEmpty()) {
            Task poll = this.mWaitQueue.poll();
            this.mLimit--;
            this.mRunningQueue.add(this.mPool.submit(poll, this));
            Log.d("TaskManager", "task %s submit", (Object) poll);
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

    public void onFutureDone(Future<Task> future) {
        Task task;
        synchronized (this.mLock) {
            if (needReschedule(future) && (task = (Task) future.getJob()) != null) {
                Log.d("TaskManager", "interrupted task %s rescheduled", (Object) task);
                this.mWaitQueue.offer(task);
            }
            this.mRunningQueue.remove(future);
            this.mLimit++;
            submitIfAllowed();
        }
    }

    public void shutdown() {
        cancelAll();
        this.mPool.shutdown();
    }

    public void submit(Task task) {
        if (task != null) {
            synchronized (this.mLock) {
                if (contains(task)) {
                    Log.d("TaskManager", "contain task %s", (Object) task);
                    return;
                }
                this.mWaitQueue.offer(task);
                Iterator it = this.mRunningQueue.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    Task task2 = (Task) future.getJob();
                    if (task.compareTo(task2) > 0) {
                        Log.d("TaskManager", "task %s is interrupted for running %s", task2, task);
                        future.cancel(1);
                    }
                }
                submitIfAllowed();
            }
        }
    }
}
