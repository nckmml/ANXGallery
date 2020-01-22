package com.android.internal;

import android.app.Activity;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.nexstreaming.nexeditorsdk.nexEngine;

public class WindowCompat {
    private static int DEFAULT_NOTCH_HEIGHT = 80;
    private static Boolean sIsNotch;
    private static Integer sTopNotchHeight;

    public static int getTopNotchHeight(Activity activity) {
        WindowInsets rootWindowInsets;
        Integer num = sTopNotchHeight;
        if (num != null) {
            return num.intValue();
        }
        if (isNotch(activity)) {
            if (Build.VERSION.SDK_INT >= 28) {
                View decorView = activity.getWindow().getDecorView();
                if (!(decorView == null || (rootWindowInsets = decorView.getRootWindowInsets()) == null)) {
                    DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
                    if (displayCutout == null) {
                        sTopNotchHeight = 0;
                    } else {
                        sTopNotchHeight = Integer.valueOf(displayCutout.getSafeInsetTop());
                    }
                    return sTopNotchHeight.intValue();
                }
            } else {
                int identifier = activity.getResources().getIdentifier("notch_height", "dimen", "android");
                if (identifier > 0) {
                    sTopNotchHeight = Integer.valueOf(activity.getResources().getDimensionPixelSize(identifier));
                } else {
                    sTopNotchHeight = Integer.valueOf(DEFAULT_NOTCH_HEIGHT);
                }
                return sTopNotchHeight.intValue();
            }
        }
        return 0;
    }

    public static boolean isNotch(Activity activity) {
        View decorView;
        WindowInsets rootWindowInsets;
        Boolean bool = sIsNotch;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z = true;
        if (Build.VERSION.SDK_INT < 28) {
            if (SystemPropertiesCompat.getInt("ro.miui.notch", 0) != 1) {
                z = false;
            }
            sIsNotch = Boolean.valueOf(z);
            return sIsNotch.booleanValue();
        } else if (activity == null || activity.isDestroyed() || (decorView = activity.getWindow().getDecorView()) == null || (rootWindowInsets = decorView.getRootWindowInsets()) == null) {
            return false;
        } else {
            if (rootWindowInsets.getDisplayCutout() == null) {
                z = false;
            }
            sIsNotch = Boolean.valueOf(z);
            return sIsNotch.booleanValue();
        }
    }

    public static void setCutoutDefaultMode(Window window) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 0;
            window.setAttributes(attributes);
        }
    }

    public static void setCutoutModeShortEdges(Window window) {
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    public static void setShowWhenLocked(Activity activity, boolean z) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= 27) {
                activity.setShowWhenLocked(z);
            } else if (z) {
                activity.getWindow().addFlags(nexEngine.ExportHEVCHighTierLevel52);
            } else {
                activity.getWindow().clearFlags(nexEngine.ExportHEVCHighTierLevel52);
            }
        }
    }
}
