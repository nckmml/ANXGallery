package micloud.compat.v18.sync;

import android.content.Context;
import com.xiaomi.micloudsdk.utils.MiCloudSdkBuild;

public class PermissionUtilsCompat {
    private static final IPermissionUtilsCompat IMPL;
    private static final int version = MiCloudSdkBuild.CURRENT_VERSION;

    static {
        if (version >= 23) {
            IMPL = new PermissionUtilsCompatMiCloud_V23();
        } else {
            IMPL = new PermissionUtilsCompatMiCloud_Base();
        }
    }

    public static boolean isGdprPermissionGranted(Context context) {
        return IMPL.isGdprPermissionGranted(context);
    }
}
