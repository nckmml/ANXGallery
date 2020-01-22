package com.miui.gallery.util.thread;

import miui.os.Environment;

public class ThreadConfig {
    public static int getSuggestThreadCoreSize() {
        return Environment.getCpuCount() + 1;
    }

    public static int getSuggestThreadMaxSize() {
        return (Environment.getCpuCount() * 2) + 1;
    }
}
