package com.miui.gallery.util;

import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.os.Rom;

public class FeatureUtil {
    public static boolean isSupportBackupOnlyWifi() {
        return CloudControlStrategyHelper.getSyncStrategy().isSupportBackupOnlyWifi();
    }

    public static boolean isSupportFastBlur() {
        return Rom.IS_STABLE;
    }

    public static boolean isSupportPublicShareAlbum() {
        return false;
    }
}
