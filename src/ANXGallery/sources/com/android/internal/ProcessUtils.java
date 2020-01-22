package com.android.internal;

import android.app.ActivityThread;

public class ProcessUtils {
    public static String currentProcessName() {
        try {
            return ActivityThread.currentProcessName();
        } catch (Exception unused) {
            return null;
        }
    }
}
