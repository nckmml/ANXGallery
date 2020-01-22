package com.xiaomi.micloudsdk.sync;

import android.util.ArrayMap;
import java.util.Map;

public class SyncCommandConstant {
    static final Map<String, String> SUPPORT_COMMAND_PKGS = new ArrayMap();

    static {
        SUPPORT_COMMAND_PKGS.put("com.miui.gallery.cloud.provider", "com.miui.gallery");
    }
}
