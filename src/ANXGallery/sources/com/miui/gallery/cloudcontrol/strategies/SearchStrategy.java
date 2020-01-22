package com.miui.gallery.cloudcontrol.strategies;

import com.google.gson.annotations.SerializedName;
import com.miui.gallery.cloudcontrol.CloudControlManager;
import com.miui.gallery.cloudcontrol.FeatureProfile;

public class SearchStrategy extends BaseStrategy {
    @SerializedName("search-data")
    private String mAIAlbum = FeatureProfile.Status.UNAVAILABLE.getValue();
    private boolean mAIAlbumEnabled = false;
    @SerializedName("search-box")
    private String mSearchBar = FeatureProfile.Status.UNAVAILABLE.getValue();
    private boolean mSearchBarEnabled = false;

    public SearchStrategy(boolean z, boolean z2) {
        this.mSearchBarEnabled = z;
        this.mAIAlbumEnabled = z2;
    }

    public static SearchStrategy createDefault() {
        boolean z = CloudControlManager.getInstance().queryFeatureStatus("search") == FeatureProfile.Status.ENABLE;
        return new SearchStrategy(z, z);
    }

    public void doAdditionalProcessing() {
        this.mAIAlbumEnabled = FeatureProfile.Status.ENABLE.getValue().equalsIgnoreCase(this.mAIAlbum);
        this.mSearchBarEnabled = this.mAIAlbumEnabled && FeatureProfile.Status.ENABLE.getValue().equalsIgnoreCase(this.mSearchBar);
    }

    public boolean isAIAlbumEnabled() {
        return this.mAIAlbumEnabled;
    }

    public boolean isSearchBarEnabled() {
        return this.mSearchBarEnabled;
    }
}
