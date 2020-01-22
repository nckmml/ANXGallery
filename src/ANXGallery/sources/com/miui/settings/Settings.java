package com.miui.settings;

import android.content.Context;
import android.os.Build;
import android.provider.SystemSettings;
import android.text.TextUtils;
import com.miui.core.SdkHelper;
import com.nexstreaming.nexeditorsdk.BuildConfig;
import java.util.Locale;

public class Settings {
    public static boolean checkRegion(String str) {
        return Build.checkRegion(str);
    }

    public static String getDeviceName(Context context) {
        if (SdkHelper.IS_MIUI) {
            String deviceName = SystemSettings.System.getDeviceName(context);
            return TextUtils.isEmpty(deviceName) ? BuildConfig.KM_PROJECT : deviceName;
        }
        return String.format(Locale.US, "%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL});
    }

    public static String getRegion() {
        return Build.getRegion();
    }
}
