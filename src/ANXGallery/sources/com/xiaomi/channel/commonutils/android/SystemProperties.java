package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;

public class SystemProperties {
    public static String get(String str, String str2) {
        try {
            return (String) SystemUtils.loadClass((Context) null, "android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (Exception e) {
            MyLog.w("SystemProperties.get: " + e);
            return str2;
        }
    }
}
