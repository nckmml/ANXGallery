package micloud.compat.v18.sync;

import android.content.Context;

interface IPermissionUtilsCompat {
    boolean isGdprPermissionGranted(Context context);
}
