package com.miui.gallery.cloudcontrol;

import com.google.gson.annotations.SerializedName;

public class RecommendItem {
    @SerializedName("description")
    private String mDescription;
    @SerializedName("iconUrl")
    private String mIconUrl;
    @SerializedName("key")
    private String mKey;
    @SerializedName("landingUrl")
    private String mLandingUrl;
    @SerializedName("name")
    private String mName;
    @SerializedName("seqId")
    private String mSeqId;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("type")
    private String mType;

    public String getDescription() {
        return this.mDescription;
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    public String getKey() {
        return this.mKey;
    }

    public String getLandingUrl() {
        return this.mLandingUrl;
    }

    public String getName() {
        return this.mName;
    }

    public String getSeqId() {
        return this.mSeqId;
    }
}
