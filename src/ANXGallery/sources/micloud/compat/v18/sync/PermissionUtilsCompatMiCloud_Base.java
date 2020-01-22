package micloud.compat.v18.sync;

import android.content.Context;

class PermissionUtilsCompatMiCloud_Base implements IPermissionUtilsCompat {
    PermissionUtilsCompatMiCloud_Base() {
    }

    public boolean isGdprPermissionGranted(Context context) {
        return true;
    }
}
