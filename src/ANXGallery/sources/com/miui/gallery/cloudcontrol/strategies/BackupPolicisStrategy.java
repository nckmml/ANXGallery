package com.miui.gallery.cloudcontrol.strategies;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.util.MiscUtil;
import java.util.HashMap;
import java.util.List;

public class BackupPolicisStrategy extends BaseStrategy {
    @SerializedName("policies")
    private List<PolicyWrapper> mPolicies;
    private transient HashMap<String, PolicyWrapper> mPoliciesMap;

    public static class Policy {
        @SerializedName("disallow-metered")
        private boolean mDisallowMetered;
        @SerializedName("expedited")
        private boolean mExpedited;
        @SerializedName("ignore-battery")
        private boolean mIgnoreBattery;
        @SerializedName("manual")
        private boolean mManual;
        @SerializedName("require-charging")
        private boolean mRequireCharging;

        public boolean isDisallowMetered() {
            return this.mDisallowMetered;
        }

        public boolean isIgnoreBattery() {
            return this.mIgnoreBattery;
        }

        public boolean isRequireCharging() {
            return this.mRequireCharging;
        }

        public String toString() {
            return "Policy{mDisallowMetered='" + this.mDisallowMetered + ", mRequireCharging=" + this.mRequireCharging + ", mIgnoreBattery=" + this.mIgnoreBattery + ", mManual=" + this.mManual + ", mExpedited=" + this.mExpedited + '}';
        }
    }

    public static class PolicyWrapper {
        @SerializedName("enable")
        private boolean mEnable;
        @SerializedName("policy")
        private Policy mPolicy;
        @SerializedName("type")
        private String mType;

        public String getType() {
            return this.mType;
        }

        public boolean isDisallowMetered() {
            Policy policy = this.mPolicy;
            if (policy != null) {
                return policy.isDisallowMetered();
            }
            return true;
        }

        public boolean isEnable() {
            return this.mEnable;
        }

        public boolean isIgnoreBattery() {
            Policy policy = this.mPolicy;
            if (policy != null) {
                return policy.isIgnoreBattery();
            }
            return false;
        }

        public boolean isRequireCharging() {
            Policy policy = this.mPolicy;
            if (policy != null) {
                return policy.isRequireCharging();
            }
            return false;
        }

        public String toString() {
            return "PolicyWrapper{mType='" + this.mType + ", mPolicy=" + this.mPolicy + '}';
        }
    }

    public void doAdditionalProcessing() {
        HashMap<String, PolicyWrapper> hashMap = this.mPoliciesMap;
        if (hashMap == null) {
            this.mPoliciesMap = new HashMap<>();
        } else {
            hashMap.clear();
        }
        if (MiscUtil.isValid(this.mPolicies)) {
            for (PolicyWrapper next : this.mPolicies) {
                if (!TextUtils.isEmpty(next.getType())) {
                    this.mPoliciesMap.put(next.getType(), next);
                }
            }
        }
    }

    public PolicyWrapper getPolicy(String str) {
        return this.mPoliciesMap.get(str);
    }

    public String toString() {
        return "BackupPolicisStrategy{mPolicies=" + this.mPolicies + '}';
    }
}
