package com.miui.gallery.search.core.context;

import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.threadpool.ThreadPool.Job;

public interface TaskExecutor<T extends ThreadPool.Job> {
    void cancel(T t);

    void submit(T t);
}
