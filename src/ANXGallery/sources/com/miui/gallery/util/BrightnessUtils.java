package com.miui.gallery.util;

import android.content.Context;
import android.os.Build;
import com.android.internal.DisplayManager;

public class BrightnessUtils {
    public static void setTemporaryAutoBrightness(Context context, float f) {
        if (Build.VERSION.SDK_INT >= 28) {
            DisplayManager.setTemporaryAutoBrightnessAdjustment(context, f);
            return;
        }
        MiscUtil.invokeSafely(context.getSystemService("power"), "setTemporaryAutoBrightnessAdjustmentRatio", new Class[]{Float.TYPE}, Float.valueOf(f));
    }
}
