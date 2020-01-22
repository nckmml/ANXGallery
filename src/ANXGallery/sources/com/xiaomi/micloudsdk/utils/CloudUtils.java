package com.xiaomi.micloudsdk.utils;

import android.annotation.SuppressLint;
import com.xiaomi.micloudsdk.exception.CloudServerException;

@SuppressLint({"NewApi"})
@Deprecated
public class CloudUtils {
    public static String updateRequestHost(String str, boolean z) throws CloudServerException {
        return com.xiaomi.micloudsdk.request.utils.CloudUtils.updateRequestHost(str, z);
    }
}
