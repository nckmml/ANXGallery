package com.miui.widget.util.async;

import android.os.HandlerThread;
import android.os.Looper;
import com.miui.widget.util.ArrayMap;
import java.util.Map;

public class WorkerThreads {
    private static final Map<String, ThreadWrapper> sWorkers = new ArrayMap();

    private static class ThreadWrapper {
        int refCount = 1;
        final HandlerThread thread;

        ThreadWrapper(String str) {
            this.thread = new HandlerThread(str);
            this.thread.start();
        }
    }

    public static synchronized Looper aquireWorker(String str) {
        Looper looper;
        synchronized (WorkerThreads.class) {
            ThreadWrapper threadWrapper = sWorkers.get(str);
            if (threadWrapper == null) {
                threadWrapper = new ThreadWrapper(str);
                sWorkers.put(str, threadWrapper);
            } else {
                threadWrapper.refCount++;
            }
            looper = threadWrapper.thread.getLooper();
        }
        return looper;
    }

    public static synchronized void releaseWorker(String str) {
        synchronized (WorkerThreads.class) {
            ThreadWrapper threadWrapper = sWorkers.get(str);
            if (threadWrapper != null) {
                threadWrapper.refCount--;
                if (threadWrapper.refCount == 0) {
                    sWorkers.remove(str);
                    threadWrapper.thread.quitSafely();
                }
            }
        }
    }
}
