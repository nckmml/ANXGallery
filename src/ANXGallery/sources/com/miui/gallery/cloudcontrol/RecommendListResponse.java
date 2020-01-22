package com.miui.gallery.cloudcontrol;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class RecommendListResponse extends CloudControlResponse {
    @SerializedName("recList")
    private ArrayList<RecommendFeatureProfile> mRecommendFeatureProfiles;

    public ArrayList<FeatureProfile> getFeatureProfiles() {
        return this.mRecommendFeatureProfiles;
    }
}
