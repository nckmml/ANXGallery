package com.miui.gallery.cloudcontrol;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;

public class RecommendFeatureProfile extends FeatureProfile {
    @SerializedName("sublist")
    protected ArrayList<RecommendItem> mSublist;

    public String getName() {
        return "recommendation";
    }

    public String getStrategy() {
        HashMap hashMap = new HashMap();
        hashMap.put("sublist", this.mSublist);
        return new Gson().toJson((Object) hashMap);
    }
}
