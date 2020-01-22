package com.android.internal;

import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import com.miui.internal.hidden.WindowInsetsReflect;

public class WindowInsetsCompat {
    private static boolean sNoMethodDetected;

    public static int getDisplayCutoutInsetsRight(View view) {
        WindowInsets rootWindowInsets;
        DisplayCutout displayCutout;
        if (Build.VERSION.SDK_INT < 28 || (rootWindowInsets = view.getRootWindowInsets()) == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null) {
            return 0;
        }
        return displayCutout.getSafeInsetRight();
    }

    public static boolean shouldAlwaysConsumeSystemBars(View view) {
        WindowInsets rootWindowInsets;
        if (sNoMethodDetected) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                WindowInsets rootWindowInsets2 = view.getRootWindowInsets();
                if (rootWindowInsets2 != null) {
                    return WindowInsetsReflect.shouldAlwaysConsumeSystemBars(rootWindowInsets2);
                }
            } else if (Build.VERSION.SDK_INT >= 24 && (rootWindowInsets = view.getRootWindowInsets()) != null) {
                return WindowInsetsReflect.shouldAlwaysConsumeNavBar(rootWindowInsets);
            }
        } catch (NoSuchMethodError e) {
            sNoMethodDetected = true;
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }
}
