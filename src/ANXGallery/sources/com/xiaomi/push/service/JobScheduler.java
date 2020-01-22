package com.xiaomi.push.service;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.concurrent.RejectedExecutionException;

public class JobScheduler {
    private static long currentTime;
    private static long lastTime = currentTime;
    private static long timerId;
    private final FinalizerHelper finalizer;
    private final SchedulerImpl impl;

    private static final class FinalizerHelper {
        private final SchedulerImpl impl;

        FinalizerHelper(SchedulerImpl schedulerImpl) {
            this.impl = schedulerImpl;
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            try {
                synchronized (this.impl) {
                    boolean unused = this.impl.finished = true;
                    this.impl.notify();
                }
                super.finalize();
            } catch (Throwable th) {
                super.finalize();
                throw th;
            }
        }
    }

    public static abstract class Job implements Runnable {
        protected int type;

        public Job(int i) {
            this.type = i;
        }
    }

    private static final class SchedulerImpl extends Thread {
        /* access modifiers changed from: private */
        public boolean cancelled;
        private long current_sleep_duration = 50;
        private volatile boolean executing = false;
        /* access modifiers changed from: private */
        public boolean finished;
        private volatile long lastJob = 0;
        /* access modifiers changed from: private */
        public TimerHeap tasks = new TimerHeap();

        private static final class TimerHeap {
            private int DEFAULT_HEAP_SIZE;
            private int deletedCancelledNumber;
            private int size;
            private TaskWraper[] timers;

            private TimerHeap() {
                this.DEFAULT_HEAP_SIZE = 256;
                this.timers = new TaskWraper[this.DEFAULT_HEAP_SIZE];
                this.size = 0;
                this.deletedCancelledNumber = 0;
            }

            private void downHeap(int i) {
                int i2 = (i * 2) + 1;
                while (true) {
                    int i3 = this.size;
                    if (i2 < i3 && i3 > 0) {
                        int i4 = i2 + 1;
                        if (i4 < i3 && this.timers[i4].when < this.timers[i2].when) {
                            i2 = i4;
                        }
                        if (this.timers[i].when >= this.timers[i2].when) {
                            TaskWraper[] taskWraperArr = this.timers;
                            TaskWraper taskWraper = taskWraperArr[i];
                            taskWraperArr[i] = taskWraperArr[i2];
                            taskWraperArr[i2] = taskWraper;
                            int i5 = i2;
                            i2 = (i2 * 2) + 1;
                            i = i5;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }

            /* access modifiers changed from: private */
            public int getTask(TaskWraper taskWraper) {
                int i = 0;
                while (true) {
                    TaskWraper[] taskWraperArr = this.timers;
                    if (i >= taskWraperArr.length) {
                        return -1;
                    }
                    if (taskWraperArr[i] == taskWraper) {
                        return i;
                    }
                    i++;
                }
            }

            private void upHeap() {
                int i = this.size - 1;
                int i2 = (i - 1) / 2;
                while (this.timers[i].when < this.timers[i2].when) {
                    TaskWraper[] taskWraperArr = this.timers;
                    TaskWraper taskWraper = taskWraperArr[i];
                    taskWraperArr[i] = taskWraperArr[i2];
                    taskWraperArr[i2] = taskWraper;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            public void delete(int i) {
                int i2;
                if (i >= 0 && i < (i2 = this.size)) {
                    TaskWraper[] taskWraperArr = this.timers;
                    int i3 = i2 - 1;
                    this.size = i3;
                    taskWraperArr[i] = taskWraperArr[i3];
                    taskWraperArr[this.size] = null;
                    downHeap(i);
                }
            }

            public void deleteIfCancelled() {
                int i = 0;
                while (i < this.size) {
                    if (this.timers[i].cancelled) {
                        this.deletedCancelledNumber++;
                        delete(i);
                        i--;
                    }
                    i++;
                }
            }

            public boolean hasJob(int i) {
                for (int i2 = 0; i2 < this.size; i2++) {
                    if (this.timers[i2].type == i) {
                        return true;
                    }
                }
                return false;
            }

            public void insert(TaskWraper taskWraper) {
                TaskWraper[] taskWraperArr = this.timers;
                int length = taskWraperArr.length;
                int i = this.size;
                if (length == i) {
                    TaskWraper[] taskWraperArr2 = new TaskWraper[(i * 2)];
                    System.arraycopy(taskWraperArr, 0, taskWraperArr2, 0, i);
                    this.timers = taskWraperArr2;
                }
                TaskWraper[] taskWraperArr3 = this.timers;
                int i2 = this.size;
                this.size = i2 + 1;
                taskWraperArr3[i2] = taskWraper;
                upHeap();
            }

            public boolean isEmpty() {
                return this.size == 0;
            }

            public TaskWraper minimum() {
                return this.timers[0];
            }

            public void removeJobs(int i) {
                for (int i2 = 0; i2 < this.size; i2++) {
                    if (this.timers[i2].type == i) {
                        this.timers[i2].cancel();
                    }
                }
                deleteIfCancelled();
            }

            public void removeJobs(int i, Job job) {
                for (int i2 = 0; i2 < this.size; i2++) {
                    if (this.timers[i2].job == job) {
                        this.timers[i2].cancel();
                    }
                }
                deleteIfCancelled();
            }

            public void reset() {
                this.timers = new TaskWraper[this.DEFAULT_HEAP_SIZE];
                this.size = 0;
            }
        }

        SchedulerImpl(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* access modifiers changed from: private */
        public void insertTask(TaskWraper taskWraper) {
            this.tasks.insert(taskWraper);
            notify();
        }

        public synchronized void cancel() {
            this.cancelled = true;
            this.tasks.reset();
            notify();
        }

        public boolean isBlocked() {
            return this.executing && SystemClock.uptimeMillis() - this.lastJob > 600000;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
            r10.lastJob = android.os.SystemClock.uptimeMillis();
            r10.executing = true;
            r2.job.run();
            r10.executing = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00aa, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ab, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
            r10.cancelled = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x00af, code lost:
            throw r1;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0018 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x005a */
        public void run() {
            while (true) {
                synchronized (this) {
                    if (!this.cancelled) {
                        if (!this.tasks.isEmpty()) {
                            long currentTime = JobScheduler.getCurrentTime();
                            TaskWraper minimum = this.tasks.minimum();
                            synchronized (minimum.lock) {
                                if (minimum.cancelled) {
                                    this.tasks.delete(0);
                                } else {
                                    long j = minimum.when - currentTime;
                                    if (j > 0) {
                                        if (j > this.current_sleep_duration) {
                                            j = this.current_sleep_duration;
                                        }
                                        this.current_sleep_duration += 50;
                                        if (this.current_sleep_duration > 500) {
                                            this.current_sleep_duration = 500;
                                        }
                                        wait(j);
                                    } else {
                                        this.current_sleep_duration = 50;
                                        synchronized (minimum.lock) {
                                            int access$100 = this.tasks.minimum().when != minimum.when ? this.tasks.getTask(minimum) : 0;
                                            if (minimum.cancelled) {
                                                this.tasks.delete(this.tasks.getTask(minimum));
                                            } else {
                                                minimum.setScheduledTime(minimum.when);
                                                this.tasks.delete(access$100);
                                                minimum.when = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (!this.finished) {
                            wait();
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    static class TaskWraper {
        boolean cancelled;
        Job job;
        final Object lock = new Object();
        private long scheduledTime;
        int type;
        long when;

        TaskWraper() {
        }

        public boolean cancel() {
            boolean z;
            synchronized (this.lock) {
                z = !this.cancelled && this.when > 0;
                this.cancelled = true;
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public void setScheduledTime(long j) {
            synchronized (this.lock) {
                this.scheduledTime = j;
            }
        }
    }

    static {
        long j = 0;
        if (SystemClock.elapsedRealtime() > 0) {
            j = SystemClock.elapsedRealtime();
        }
        currentTime = j;
    }

    public JobScheduler() {
        this(false);
    }

    public JobScheduler(String str) {
        this(str, false);
    }

    public JobScheduler(String str, boolean z) {
        if (str != null) {
            this.impl = new SchedulerImpl(str, z);
            this.finalizer = new FinalizerHelper(this.impl);
            return;
        }
        throw new NullPointerException("name == null");
    }

    public JobScheduler(boolean z) {
        this("Timer-" + nextId(), z);
    }

    static synchronized long getCurrentTime() {
        long j;
        synchronized (JobScheduler.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > lastTime) {
                currentTime += elapsedRealtime - lastTime;
            }
            lastTime = elapsedRealtime;
            j = currentTime;
        }
        return j;
    }

    private static synchronized long nextId() {
        long j;
        synchronized (JobScheduler.class) {
            j = timerId;
            timerId = 1 + j;
        }
        return j;
    }

    private void scheduleImpl(Job job, long j) {
        synchronized (this.impl) {
            if (!this.impl.cancelled) {
                long currentTime2 = j + getCurrentTime();
                if (currentTime2 >= 0) {
                    TaskWraper taskWraper = new TaskWraper();
                    taskWraper.type = job.type;
                    taskWraper.job = job;
                    taskWraper.when = currentTime2;
                    this.impl.insertTask(taskWraper);
                } else {
                    throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + currentTime2);
                }
            } else {
                throw new IllegalStateException("Timer was canceled");
            }
        }
    }

    public void executeJobDelayed(Job job, long j) {
        if (j >= 0) {
            scheduleImpl(job, j);
            return;
        }
        throw new IllegalArgumentException("delay < 0: " + j);
    }

    public void executeJobNow(Job job) {
        if (MyLog.getLogLevel() >= 1 || Thread.currentThread() == this.impl) {
            job.run();
        } else {
            MyLog.e("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public boolean hasJob(int i) {
        boolean hasJob;
        synchronized (this.impl) {
            hasJob = this.impl.tasks.hasJob(i);
        }
        return hasJob;
    }

    public boolean isBlocked() {
        return this.impl.isBlocked();
    }

    public void quit() {
        this.impl.cancel();
    }

    public void removeAllJobs() {
        synchronized (this.impl) {
            this.impl.tasks.reset();
        }
    }

    public void removeJobs(int i) {
        synchronized (this.impl) {
            this.impl.tasks.removeJobs(i);
        }
    }

    public void removeJobs(int i, Job job) {
        synchronized (this.impl) {
            this.impl.tasks.removeJobs(i, job);
        }
    }
}
