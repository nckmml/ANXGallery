package com.xiaomi.channel.commonutils.android;

import android.content.SharedPreferences;
import android.os.Build;

public final class SharedPrefsCompat {
    public static void apply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
