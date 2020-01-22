package com.miui.gallery.cloudcontrol.strategies;

import com.google.gson.annotations.SerializedName;
import com.miui.extraphoto.sdk.ExtraPhotoSDK;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.cloudcontrol.RecommendItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RecommendStrategy extends BaseStrategy {
    private static final Map<String, Boolean> sFeatureList = new HashMap<String, Boolean>() {
        {
            put("slowAdjustment", false);
            put("smartSoundtrack", false);
            put("dynamicSpot", Boolean.valueOf(ExtraPhotoSDK.isDeviceSupportRefocus(GalleryApp.sGetAndroidContext())));
            put("puzzle", true);
            put("studioLightEffect", Boolean.valueOf(ExtraPhotoSDK.isDeviceSupportRefocus(GalleryApp.sGetAndroidContext())));
            put("photoMovie", true);
            put("storyAlbum", Boolean.valueOf(ImageFeatureManager.isDeviceSupportStoryFunction()));
            put("banner", true);
        }
    };
    @SerializedName("sublist")
    private ArrayList<RecommendItem> mCandidateList;

    private ArrayList<RecommendItem> filter() {
        ArrayList<RecommendItem> arrayList = new ArrayList<>();
        Iterator<RecommendItem> it = this.mCandidateList.iterator();
        while (it.hasNext()) {
            RecommendItem next = it.next();
            if (sFeatureList.keySet().contains(next.getKey()) && sFeatureList.get(next.getKey()).booleanValue()) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, new Comparator<RecommendItem>() {
            public int compare(RecommendItem recommendItem, RecommendItem recommendItem2) {
                if (recommendItem.getKey().equals("banner")) {
                    return -1;
                }
                if (recommendItem2.getKey().equals("banner")) {
                    return 1;
                }
                return Integer.compare(Integer.valueOf(recommendItem2.getSeqId()).intValue(), Integer.valueOf(recommendItem.getSeqId()).intValue());
            }
        });
        return arrayList;
    }

    public int getMaxSeqId() {
        Iterator<RecommendItem> it = this.mCandidateList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = Integer.valueOf(it.next().getSeqId()).intValue();
            if (intValue > i) {
                i = intValue;
            }
        }
        return i;
    }

    public ArrayList<RecommendItem> getRecommendItems() {
        return filter();
    }
}
