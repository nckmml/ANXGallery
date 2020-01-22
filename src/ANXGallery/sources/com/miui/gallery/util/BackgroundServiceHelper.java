package com.miui.gallery.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BackgroundServiceHelper {
    public static void startForegroundServiceIfNeed(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
