package com.miui.gallery.threadpool;

import com.miui.gallery.threadpool.ThreadPool;

public interface Future<T> {
    void cancel();

    void cancel(int i);

    T get();

    int getCancelType();

    ThreadPool.Job<T> getJob();

    boolean isCancelled();

    boolean isDone();
}
