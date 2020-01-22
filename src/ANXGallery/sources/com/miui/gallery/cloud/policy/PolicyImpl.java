package com.miui.gallery.cloud.policy;

import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloudcontrol.strategies.BackupPolicisStrategy;
import com.miui.gallery.preference.GalleryPreferences;

public class PolicyImpl implements IPolicy {
    private final BackupPolicisStrategy.PolicyWrapper mProxy;
    private final SyncType mType;

    public PolicyImpl(SyncType syncType, BackupPolicisStrategy.PolicyWrapper policyWrapper) {
        this.mType = syncType;
        this.mProxy = policyWrapper;
    }

    public SyncType getType() {
        return this.mType;
    }

    public boolean isDisallowMetered() {
        if (!GalleryPreferences.Sync.getBackupOnlyInWifi()) {
            return false;
        }
        BackupPolicisStrategy.PolicyWrapper policyWrapper = this.mProxy;
        if (policyWrapper != null) {
            return policyWrapper.isDisallowMetered();
        }
        return true;
    }

    public boolean isEnable() {
        BackupPolicisStrategy.PolicyWrapper policyWrapper = this.mProxy;
        if (policyWrapper != null) {
            return policyWrapper.isEnable();
        }
        return true;
    }

    public boolean isIgnoreBatteryLow() {
        BackupPolicisStrategy.PolicyWrapper policyWrapper = this.mProxy;
        if (policyWrapper != null) {
            return policyWrapper.isIgnoreBattery();
        }
        return false;
    }

    public boolean isRequireCharging() {
        BackupPolicisStrategy.PolicyWrapper policyWrapper = this.mProxy;
        if (policyWrapper != null) {
            return policyWrapper.isRequireCharging();
        }
        return false;
    }
}
