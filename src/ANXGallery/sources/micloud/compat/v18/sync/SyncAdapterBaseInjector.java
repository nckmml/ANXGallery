package micloud.compat.v18.sync;

import android.content.Context;
import android.content.SyncResult;
import com.xiaomi.micloudsdk.utils.MiCloudSdkBuild;

public class SyncAdapterBaseInjector {
    private static final ISyncAdapterBaseInjector IMPL;

    static {
        if (MiCloudSdkBuild.CURRENT_VERSION >= 23) {
            IMPL = new SyncAdapterBaseInjectorMiCloud_V23();
        } else {
            IMPL = new SyncAdapterBaseInjectorMiCloud_Base();
        }
    }

    public static boolean isGdprPermissionGranted(Context context, SyncResult syncResult) {
        return IMPL.isGdprPermissionGranted(context, syncResult);
    }
}
