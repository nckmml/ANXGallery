package micloud.compat.v18.utils;

import android.content.Context;
import com.xiaomi.micloudsdk.utils.MiCloudSdkBuild;

public class PrivacyIntentHelperCompat {
    private static final IPrivacyIntentHelperCompat IMPL;
    private static final int version = MiCloudSdkBuild.CURRENT_VERSION;

    static {
        if (version >= 24) {
            IMPL = new PrivacyIntentHelperCompatMiCloud_V24();
        } else {
            IMPL = new PrivacyIntentHelperCompatMiCloud_Base();
        }
    }

    public static void notifyPrivacyDenied(Context context) {
        IMPL.notifyPrivacyDenied(context);
    }
}
