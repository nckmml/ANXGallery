package micloud.compat.v18.sync;

import android.content.Context;
import android.content.SyncResult;

class SyncAdapterBaseInjectorMiCloud_Base implements ISyncAdapterBaseInjector {
    SyncAdapterBaseInjectorMiCloud_Base() {
    }

    public boolean isGdprPermissionGranted(Context context, SyncResult syncResult) {
        return true;
    }
}
