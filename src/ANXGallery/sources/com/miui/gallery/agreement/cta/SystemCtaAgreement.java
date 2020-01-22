package com.miui.gallery.agreement.cta;

import android.content.Context;
import android.content.Intent;
import com.miui.gallery.util.LazyValue;
import miui.util.AppConstants;

public class SystemCtaAgreement {
    public static LazyValue<Context, Boolean> SUPPORT_SYSTEM_CTA = new LazyValue<Context, Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean onInit(Context context) {
            boolean z = false;
            if (AppConstants.getSdkLevel(context, "com.miui.core") < 18) {
                return false;
            }
            Intent intent = new Intent("miui.intent.action.SYSTEM_PERMISSION_DECLARE");
            intent.setPackage("com.miui.securitycenter");
            try {
                if (!context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    };
}
