package micloud.compat.v18.sync;

import android.content.Context;
import android.provider.Settings;

class PermissionUtilsCompatMiCloud_V23 extends PermissionUtilsCompatMiCloud_Base {
    PermissionUtilsCompatMiCloud_V23() {
    }

    public boolean isGdprPermissionGranted(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "micloud_gdpr_permission_granted", 1) != 0;
    }
}
