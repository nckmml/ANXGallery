package com.miui.gallery.util;

import android.content.Context;
import android.os.Build;
import com.android.internal.SystemPropertiesCompat;
import com.miui.os.FeatureHelper;
import com.miui.settings.Settings;
import java.io.File;

public class BuildUtil extends BaseBuildUtil {
    public static final boolean IS_M_OR_LATER = (Build.VERSION.SDK_INT >= 23);
    private static final ModuleLevel MIUI_SDK_LEVEL = new ModuleLevel("com.miui.core");
    private static Boolean sIsOLED;
    private static Boolean sIsTablet;

    public static String getDeviceName(Context context) {
        return Settings.getDeviceName(context);
    }

    public static boolean isDefaultLockStyle() {
        return !new File("/data/system/theme//lockscreen").exists();
    }

    public static boolean isMiui10(Context context) {
        return ((Integer) MIUI_SDK_LEVEL.get(context)).intValue() < 18;
    }

    public static boolean isOledDisplay() {
        if (sIsOLED == null) {
            sIsOLED = Boolean.valueOf("oled".equals(SystemPropertiesCompat.get("ro.display.type", "default")));
        }
        return sIsOLED.booleanValue();
    }

    public static boolean isPad() {
        return FeatureHelper.isPad();
    }

    public static boolean isTablet() {
        if (sIsTablet == null) {
            sIsTablet = Boolean.valueOf(SystemPropertiesCompat.get("ro.build.characteristics", "default").contains("tablet"));
        }
        return sIsTablet.booleanValue();
    }

    public static boolean isXiaoMi() {
        return FeatureHelper.isXiaoMi();
    }
}
