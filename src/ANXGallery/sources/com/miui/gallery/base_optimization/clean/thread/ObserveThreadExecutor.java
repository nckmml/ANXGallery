package com.miui.gallery.base_optimization.clean.thread;

import io.reactivex.Scheduler;

public interface ObserveThreadExecutor {
    Scheduler getScheduler();
}
