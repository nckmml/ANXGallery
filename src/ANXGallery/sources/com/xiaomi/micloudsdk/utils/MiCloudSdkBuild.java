package com.xiaomi.micloudsdk.utils;

import miui.util.Log;

public class MiCloudSdkBuild {
    public static final int CURRENT_VERSION = getCurrentVersion();

    static {
        Log.i("MiCloudSdkBuild", "MiCloudSdk version:" + CURRENT_VERSION);
    }

    private static int getCurrentVersion() {
        Class loadClass = ReflectUtils.loadClass("com.xiaomi.micloudsdk.os.MiCloudSdkVersion");
        if (loadClass != null) {
            return ReflectUtils.getStaticFieldIntValue(loadClass, "version");
        }
        if (ReflectUtils.loadClass("miui.cloud.helper.BroadcastIntentHelper") != null) {
            return 25;
        }
        if (ReflectUtils.loadClass("com.xiaomi.micloudsdk.utils.MiCloudRuntimeConstants") != null) {
            return 18;
        }
        throw new RuntimeException("No MiCloudSDK runtime!");
    }
}
