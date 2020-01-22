package com.miui.privacy;

import android.content.Context;
import android.provider.Settings;

class XmsWrapper implements IPrivacyWrapper {
    private Context mContext;

    public XmsWrapper(Context context) {
        this.mContext = context;
    }

    public boolean isPrivacyPasswordEnabled() {
        return 1 == Settings.Secure.getInt(this.mContext.getContentResolver(), "privacy_password_is_open", 0);
    }

    public boolean isPrivateGalleryEnabled() {
        return false;
    }

    public void setPrivateGalleryEnabled(boolean z) {
    }
}
