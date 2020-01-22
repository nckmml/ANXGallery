package micloud.compat.v18.utils;

import android.content.Context;
import android.content.Intent;

class PrivacyIntentHelperCompatMiCloud_V24 extends PrivacyIntentHelperCompatMiCloud_Base {
    PrivacyIntentHelperCompatMiCloud_V24() {
    }

    public void notifyPrivacyDenied(Context context) {
        Intent intent = new Intent("com.xiaomi.action.PRIVACY_DENIED");
        intent.setPackage("com.miui.cloudservice");
        if (context.getPackageManager().resolveService(intent, 0) != null) {
            context.startService(intent);
        } else {
            context.sendBroadcast(intent);
        }
    }
}
