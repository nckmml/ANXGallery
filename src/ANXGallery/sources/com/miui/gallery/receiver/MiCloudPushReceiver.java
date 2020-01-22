package com.miui.gallery.receiver;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.miui.gallery.cloud.SpaceFullHandler;
import com.miui.gallery.util.Log;

public class MiCloudPushReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("MiCloudPushReceiver", "onReceive: %s", (Object) intent);
        String stringExtra = intent.getStringExtra("pushType");
        String stringExtra2 = intent.getStringExtra("pushName");
        Log.i("MiCloudPushReceiver", "pushType: %s, pushName: %s, pushData: %s", stringExtra, stringExtra2, intent.getStringExtra("pushData"));
        if (!"notification".equals(stringExtra)) {
            "watermark".equals(stringExtra);
        } else if ("micloud.quota.change".equals(stringExtra2)) {
            Log.i("MiCloudPushReceiver", "handleSpaceFull");
            SpaceFullHandler.handleSpaceFullIfNeeded(context);
        }
    }
}
