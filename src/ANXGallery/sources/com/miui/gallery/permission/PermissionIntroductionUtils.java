package com.miui.gallery.permission;

import android.app.Activity;
import com.miui.gallery.agreement.AgreementsUtils;
import com.miui.gallery.permission.core.OnPermissionIntroduced;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.permission.cta.CtaPermissions;
import com.miui.gallery.permission.introduction.CTAPermissionIntroduction;
import com.miui.gallery.permission.introduction.RuntimePermissionsIntroduction;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.util.BaseBuildUtil;

public class PermissionIntroductionUtils {
    public static void showPermissionIntroduction(Activity activity, String str, OnPermissionIntroduced onPermissionIntroduced) {
        if (AgreementsUtils.isKoreaRegion()) {
            new RuntimePermissionsIntroduction().introduce(activity, str, onPermissionIntroduced);
        } else if (BaseBuildUtil.isInternational()) {
            onPermissionIntroduced.onPermissionIntroduced(true);
        } else if (CtaPermissions.isPrivacyAllowed(str)) {
            onPermissionIntroduced.onPermissionIntroduced(true);
        } else if (BaseGalleryPreferences.PermissionIntroduction.containCtaPrivacyPermission(str) || !PermissionUtils.CUSTOM_REQUEST_PERMISSION.get(activity).booleanValue()) {
            new CTAPermissionIntroduction().introduce(activity, str, onPermissionIntroduced);
        } else {
            BaseGalleryPreferences.PermissionIntroduction.setCtaPrivacyPermissionsAllowed(str, true);
            onPermissionIntroduced.onPermissionIntroduced(true);
        }
    }
}
