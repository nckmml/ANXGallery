package com.miui.gallery.cloud.taskmanager;

import com.miui.gallery.threadpool.ThreadPool;

public abstract class Task<T> implements ThreadPool.Job<T>, Comparable<Task> {
    public abstract int compareTo(Task task);
}
