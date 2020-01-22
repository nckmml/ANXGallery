package com.miui.gallery.util.thread;

import com.miui.gallery.base_optimization.clean.thread.SubScribeThreadExecutor;

public class UserThreadExecutor implements SubScribeThreadExecutor {
    public void execute(Runnable runnable) {
        ThreadManager.execute(31, runnable);
    }
}
