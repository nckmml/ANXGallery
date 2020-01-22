package com.miui.gallery.util;

import android.os.Build;
import com.miui.os.Rom;
import com.miui.settings.Settings;

public class BaseBuildUtil {
    public static final boolean IS_DEBUG_BUILD = (Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug"));

    public static String getRegion() {
        return Settings.getRegion();
    }

    public static boolean isInternational() {
        return Rom.IS_INTERNATIONAL;
    }

    public static boolean isUUIDSDCard() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
