package com.miui.internal.hidden;

import android.view.WindowInsets;

public class WindowInsetsReflect {
    public static boolean shouldAlwaysConsumeNavBar(WindowInsets windowInsets) {
        return windowInsets.shouldAlwaysConsumeNavBar();
    }

    public static boolean shouldAlwaysConsumeSystemBars(WindowInsets windowInsets) {
        return windowInsets.shouldAlwaysConsumeSystemBars();
    }
}
