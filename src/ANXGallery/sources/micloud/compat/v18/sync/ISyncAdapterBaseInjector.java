package micloud.compat.v18.sync;

import android.content.Context;
import android.content.SyncResult;

interface ISyncAdapterBaseInjector {
    boolean isGdprPermissionGranted(Context context, SyncResult syncResult);
}
