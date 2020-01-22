package com.miui.gallery.util;

import android.os.Build;
import android.view.View;
import android.view.Window;

public class SystemUiUtil {
    public static void extendToStatusBar(View view) {
        view.setSystemUiVisibility(1280);
    }

    public static void hideSystemBars(View view) {
        hideSystemBars(view, !BaseMiscUtil.isNightMode(view.getContext()));
    }

    public static void hideSystemBars(View view, boolean z) {
        if (Build.VERSION.SDK_INT < 23 || !z) {
            view.setSystemUiVisibility(5894);
        } else {
            view.setSystemUiVisibility(14086);
        }
    }

    public static void hideSystemBars(View view, boolean z, boolean z2) {
        int i = (Build.VERSION.SDK_INT < 23 || !z) ? 5894 : 14086;
        if (Build.VERSION.SDK_INT >= 28 && z2) {
            i &= -5;
        }
        view.setSystemUiVisibility(i);
    }

    public static void setDrawSystemBarBackground(Window window) {
        if (Build.VERSION.SDK_INT >= 23 && window != null) {
            window.addFlags(Integer.MIN_VALUE);
        }
    }

    public static void setLayoutFullScreen(View view, boolean z) {
        int i = !z ? 5894 : 1792;
        if (Build.VERSION.SDK_INT > 22 && !BaseMiscUtil.isNightMode(view.getContext())) {
            i |= 8192;
        }
        view.setSystemUiVisibility(i);
    }

    public static void setSystemBarsVisibility(boolean z, View view) {
        boolean z2 = !BaseMiscUtil.isNightMode(view.getContext());
        if (z) {
            showSystemBars(view, z2);
        } else {
            hideSystemBars(view, z2);
        }
    }

    public static void setSystemBarsVisibility(boolean z, View view, boolean z2) {
        boolean z3 = !BaseMiscUtil.isNightMode(view.getContext());
        if (z) {
            showSystemBars(view, z3);
        } else {
            hideSystemBars(view, z3, z2);
        }
    }

    public static void showSystemBars(View view) {
        showSystemBars(view, !BaseMiscUtil.isNightMode(view.getContext()));
    }

    public static void showSystemBars(View view, boolean z) {
        if (Build.VERSION.SDK_INT < 23 || !z) {
            view.setSystemUiVisibility(1792);
        } else {
            view.setSystemUiVisibility(9984);
        }
    }
}
