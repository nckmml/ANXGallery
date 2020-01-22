package com.miui.gallery.util.thread;

import android.os.Process;
import com.miui.gallery.threadpool.ThreadPool;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadManager extends com.miui.gallery.threadpool.ThreadManager {
    private static RejectedExecutionHandler DEFAULT_REJECT_EDEXECUTION_HANDLER = new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        }
    };
    private static volatile ThreadPool sInnerPool;
    private static Object sInnerPoolLock = new Object();
    private static volatile ThreadPool sRightNowPool;
    private static Object sRightNowPoolLock = new Object();

    private static class ThreadFactory implements java.util.concurrent.ThreadFactory {
        private static final AtomicInteger THREAD_NUMBERING = new AtomicInteger();
        private int priority;

        public ThreadFactory(int i) {
            this.priority = i;
        }

        public Thread newThread(Runnable runnable) {
            Process.setThreadPriority(this.priority);
            if (runnable instanceof GalleryRunnable) {
                return new GalleryThread(runnable, ((GalleryRunnable) runnable).getName());
            }
            return new GalleryThread(runnable, "galleryThread_" + THREAD_NUMBERING.getAndIncrement());
        }
    }

    public static void execute(int i, Runnable runnable) {
        if (i == 31) {
            if (sRightNowPool == null) {
                synchronized (sRightNowPoolLock) {
                    if (sRightNowPool == null) {
                        sRightNowPool = new ThreadPool(ThreadConfig.getSuggestThreadCoreSize(), ThreadConfig.getSuggestThreadMaxSize(), new ThreadFactory(0), 32, DEFAULT_REJECT_EDEXECUTION_HANDLER);
                    }
                }
            }
            sRightNowPool.asExecutorService().execute(runnable);
        } else if (i == 47 || i == 63) {
            if (sInnerPool == null) {
                synchronized (sInnerPoolLock) {
                    if (sInnerPool == null) {
                        sInnerPool = new ThreadPool(ThreadConfig.getSuggestThreadCoreSize(), ThreadConfig.getSuggestThreadMaxSize(), new ThreadFactory(10), 64, DEFAULT_REJECT_EDEXECUTION_HANDLER);
                    }
                }
            }
            sInnerPool.asExecutorService().execute(runnable);
        }
    }
}
