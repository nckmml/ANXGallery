package com.miui.gallery.permission.introduction;

import android.app.Activity;
import android.app.DialogFragment;
import com.miui.gallery.permission.core.OnPermissionIntroduced;
import com.miui.gallery.permission.cta.CtaPermissionIntroduceDialog;
import com.miui.gallery.preference.BaseGalleryPreferences;

public class CTAPermissionIntroduction {
    public void introduce(Activity activity, final String str, final OnPermissionIntroduced onPermissionIntroduced) {
        String str2 = "cta_privacy_permission_dialog_" + str;
        DialogFragment dialogFragment = (DialogFragment) activity.getFragmentManager().findFragmentByTag(str2);
        if (dialogFragment == null) {
            dialogFragment = CtaPermissionIntroduceDialog.newInstance(str, new OnPermissionIntroduced() {
                public void onPermissionIntroduced(boolean z) {
                    BaseGalleryPreferences.PermissionIntroduction.setCtaPrivacyPermissionsAllowed(str, z);
                    onPermissionIntroduced.onPermissionIntroduced(z);
                }
            });
        }
        if (!dialogFragment.isAdded()) {
            dialogFragment.show(activity.getFragmentManager(), str2);
        }
    }
}
