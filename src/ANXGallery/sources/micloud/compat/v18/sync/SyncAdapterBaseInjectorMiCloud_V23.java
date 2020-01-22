package micloud.compat.v18.sync;

import android.content.Context;
import android.content.SyncResult;

class SyncAdapterBaseInjectorMiCloud_V23 extends SyncAdapterBaseInjectorMiCloud_Base {
    private static final PermissionUtilsCompatMiCloud_V23 permissionUtils = new PermissionUtilsCompatMiCloud_V23();

    SyncAdapterBaseInjectorMiCloud_V23() {
    }

    public boolean isGdprPermissionGranted(Context context, SyncResult syncResult) {
        boolean isGdprPermissionGranted = permissionUtils.isGdprPermissionGranted(context);
        if (!isGdprPermissionGranted) {
            syncResult.stats.numAuthExceptions++;
        }
        return isGdprPermissionGranted;
    }
}
