package com.miui.gallery.reddot;

import com.miui.gallery.preference.GalleryPreferences;

public class RedDotAtom extends RedDot {
    private boolean mIsPushValid;
    private boolean mIsUpdate;

    RedDotAtom(String str, boolean z, boolean z2) {
        super(str);
        this.mIsUpdate = z;
        this.mIsPushValid = z2;
    }

    private void setRedDotUsed() {
        GalleryPreferences.FeatureRedDot.setUpdateFeatureUsed(this.mKey, true);
        if (GalleryPreferences.FeatureRedDot.isFeatureRedDotValid(this.mKey, System.currentTimeMillis())) {
            GalleryPreferences.FeatureRedDot.setFeatureRedDotValidTime(this.mKey, 0, 0);
        }
        GalleryPreferences.FeatureRedDot.setRedDotSawTime(this.mKey, 0);
    }

    public void onClick() {
        setRedDotUsed();
    }

    public void onSaw() {
        if (DisplayStatusManager.getRedDotStatus(this.mKey, false) && GalleryPreferences.FeatureRedDot.getRedDotSawTime(this.mKey) == 0) {
            GalleryPreferences.FeatureRedDot.setRedDotSawTime(this.mKey, System.currentTimeMillis());
        }
    }

    public void onUpdate() {
        GalleryPreferences.FeatureRedDot.setUpdateFeatureUsed(this.mKey, false);
    }

    public boolean processDisplayStatus() {
        return queryOriginalStatus();
    }

    public boolean queryOriginalStatus() {
        if ((!this.mIsUpdate || GalleryPreferences.FeatureRedDot.hasUpdateFeatureUsed(this.mKey)) && (!this.mIsPushValid || !GalleryPreferences.FeatureRedDot.isFeatureRedDotValid(this.mKey, System.currentTimeMillis()))) {
            return false;
        }
        if (GalleryPreferences.FeatureRedDot.getRedDotSawTime(this.mKey) == 0 || System.currentTimeMillis() - GalleryPreferences.FeatureRedDot.getRedDotSawTime(this.mKey) < 172800000) {
            return true;
        }
        setRedDotUsed();
        return false;
    }
}
