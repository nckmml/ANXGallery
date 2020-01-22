package com.miui.gallery.cloud.control;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ReceiverUtils;

public class BatteryMonitor {
    private int mCount;
    private BroadcastReceiver mPowerIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                GalleryPreferences.Sync.setPowerCanSync(BatteryMonitor.isPowerCanSync(context, intent));
            }
        }
    };

    public static class Holder {
        /* access modifiers changed from: private */
        public static final BatteryMonitor sInstance = new BatteryMonitor();
    }

    public static BatteryMonitor getInstance() {
        return Holder.sInstance;
    }

    public static boolean isPowerCanSync(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("status", -1);
        boolean z = intExtra == 2 || intExtra == 5;
        Log.d("BatteryMonitor", "ACTION_BATTERY_CHANGED, plugged:" + z);
        if (context != null) {
            if (z) {
                GalleryPreferences.Sync.setIsPlugged(true);
                Log.d("BatteryMonitor", "power is connected");
            } else {
                GalleryPreferences.Sync.setIsPlugged(false);
                Log.d("BatteryMonitor", "power disconnected");
            }
        }
        if (z) {
            return true;
        }
        int intExtra2 = (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100);
        Log.d("BatteryMonitor", "ACTION_BATTERY_CHANGED, power remaining:" + intExtra2);
        return intExtra2 > 20;
    }

    public synchronized void end() {
        this.mCount--;
        if (this.mCount == 0) {
            ReceiverUtils.safeUnregisterReceiver(GalleryApp.sGetAndroidContext(), this.mPowerIntentReceiver);
        }
    }

    public synchronized void start() {
        if (this.mCount == 0) {
            GalleryApp.sGetAndroidContext().registerReceiver(this.mPowerIntentReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }
        this.mCount++;
    }
}
