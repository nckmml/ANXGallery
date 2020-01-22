package com.miui.gallery.util.deprecated;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.miui.gallery.preference.BaseGalleryPreferences;
import com.miui.gallery.util.StaticContext;

public class BaseDeprecatedPreference {
    private static SharedPreferences getDefaultPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean sCanConnectNetworkByImpunity() {
        return sGetDefaultPreferences().getBoolean(BaseGalleryPreferences.PrefKeys.CTA_CAN_CONNECT_NETWORK_BY_IMPUNITY, false);
    }

    protected static SharedPreferences.Editor sGetDefaultEditor() {
        return sGetDefaultPreferences().edit();
    }

    public static SharedPreferences sGetDefaultPreferences() {
        return getDefaultPreferences(StaticContext.sGetAndroidContext());
    }
}
