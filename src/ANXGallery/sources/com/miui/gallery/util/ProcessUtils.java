package com.miui.gallery.util;

import android.os.Process;
import android.text.TextUtils;

public class ProcessUtils {
    public static String currentProcessName() {
        String currentProcessName = com.android.internal.ProcessUtils.currentProcessName();
        return TextUtils.isEmpty(currentProcessName) ? miui.os.ProcessUtils.getProcessNameByPid(Process.myPid()) : currentProcessName;
    }
}
