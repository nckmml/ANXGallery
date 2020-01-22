package com.miui.gallery.cloud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MiCloudStatusInfoReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.xiaomi.action.MICLOUD_STATUS_INFO_CHANGED".equals(intent.getAction())) {
            SpaceFullHandler.handleSpaceFullIfNeeded(context);
        }
    }
}
