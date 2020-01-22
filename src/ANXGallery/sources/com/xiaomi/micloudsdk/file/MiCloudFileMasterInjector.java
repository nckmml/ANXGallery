package com.xiaomi.micloudsdk.file;

import android.content.Context;
import micloud.compat.v18.sync.PermissionUtilsCompat;

public class MiCloudFileMasterInjector {
    public static boolean checkDownloadConditions(Context context) {
        return PermissionUtilsCompat.isGdprPermissionGranted(context);
    }

    public static boolean checkUploadConditions(Context context) {
        return PermissionUtilsCompat.isGdprPermissionGranted(context);
    }
}
