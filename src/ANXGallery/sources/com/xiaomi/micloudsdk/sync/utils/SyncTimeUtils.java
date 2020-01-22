package com.xiaomi.micloudsdk.sync.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SyncTimeUtils {
    public static synchronized long getGuardDelayUntilInSec(Context context, String str) {
        synchronized (SyncTimeUtils.class) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            long currentTimeMillis = System.currentTimeMillis();
            String format = String.format("SyncWindowStart_%s", new Object[]{str});
            String format2 = String.format("SyncTimes_%s", new Object[]{str});
            long j = defaultSharedPreferences.getLong(format, 0);
            int i = defaultSharedPreferences.getInt(format2, 1);
            if (Math.abs(currentTimeMillis - j) >= 600000) {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putLong(format, currentTimeMillis);
                edit.putInt(format2, 1);
                edit.commit();
                return 0;
            } else if (i > 30) {
                long j2 = ((j + 600000) - currentTimeMillis) / 1000;
                return j2;
            } else {
                defaultSharedPreferences.edit().putInt(format2, i + 1).commit();
                return 0;
            }
        }
    }
}
