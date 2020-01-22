package com.miui.gallery.reddot;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.adapter.AssistantPageHeaderAdapter;
import com.miui.gallery.assistant.manager.ImageFeatureManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.PhotoMovieEntranceUtils;
import com.miui.gallery.util.PrintInstallHelper;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import com.nexstreaming.nexeditorsdk.nexEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayStatusManager {
    private static String TAG = "DisplayStatusManager";
    private static int sDisplayStatus;
    private static Map<String, Integer> sKeyPositionMap = new HashMap<String, Integer>() {
        {
            put("collage", 2);
            put("photo_movie", 4);
            put("photo_cleaner", 8);
            put("trash_bin", 16);
            put("photo_print", 32);
            put("settings", 64);
            put("story_album", 128);
            put("recommendation", 256);
            put("filter_sky", 512);
            put("action_bar_more", Integer.valueOf(nexEngine.ExportHEVCMainTierLevel6));
            put("assistant_tab", Integer.valueOf(nexEngine.ExportHEVCHighTierLevel6));
            put("root", Integer.MIN_VALUE);
        }
    };
    private static final Map<String, RedDot> sRedDotMap = new HashMap();

    private static void flatMap(RedDot redDot) {
        sRedDotMap.put(redDot.getKey(), redDot);
        if (!(redDot instanceof RedDotAtom) && (redDot instanceof RedDotGroup)) {
            for (RedDot flatMap : ((RedDotGroup) redDot).mPriorityGroup) {
                flatMap(flatMap);
            }
        }
    }

    private static void generateRedDotMap() {
        RedDotAtom redDotAtom;
        RedDotAtom redDotAtom2;
        RedDotAtom redDotAtom3 = new RedDotAtom("collage", true, false);
        RedDotAtom redDotAtom4 = new RedDotAtom("photo_movie", true, false);
        RedDotAtom redDotAtom5 = new RedDotAtom("photo_cleaner", true, false);
        RedDotAtom redDotAtom6 = new RedDotAtom("trash_bin", false, false);
        RedDotAtom redDotAtom7 = new RedDotAtom("photo_print", true, true);
        RedDotAtom redDotAtom8 = new RedDotAtom("settings", false, false);
        if (AssistantPageHeaderAdapter.replaceRecommend()) {
            redDotAtom2 = new RedDotAtom("recommendation", false, false);
            redDotAtom = new RedDotAtom("filter_sky", true, false);
        } else {
            redDotAtom2 = new RedDotAtom("recommendation", true, false);
            redDotAtom = new RedDotAtom("filter_sky", false, false);
        }
        RedDotAtom redDotAtom9 = new RedDotAtom("story_album", false, false);
        ArrayList arrayList = new ArrayList();
        if (ImageFeatureManager.isDeviceSupportStoryFunction()) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(redDotAtom);
            arrayList2.add(redDotAtom4);
            arrayList2.add(redDotAtom3);
            arrayList2.add(redDotAtom2);
            arrayList2.add(redDotAtom9);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(redDotAtom5);
            arrayList3.add(redDotAtom8);
            if (ApplicationHelper.isCloudTrashBinFeatureOpen() && SyncUtil.existXiaomiAccount(GalleryApp.sGetAndroidContext())) {
                arrayList3.add(redDotAtom6);
            }
            if (PrintInstallHelper.getInstance().isPhotoPrintEnable()) {
                arrayList3.add(redDotAtom7);
            }
            RedDotGroup redDotGroup = new RedDotGroup("action_bar_more", arrayList3, 1);
            arrayList.add(new RedDotGroup("assistant_tab", arrayList2, 2));
            arrayList.add(redDotGroup);
        } else {
            ArrayList arrayList4 = new ArrayList();
            if (PhotoMovieEntranceUtils.isPhotoMovieAvailable()) {
                arrayList4.add(redDotAtom4);
            }
            arrayList4.add(redDotAtom3);
            arrayList4.add(redDotAtom5);
            if (PrintInstallHelper.getInstance().isPhotoPrintEnable()) {
                arrayList4.add(redDotAtom7);
            }
            if (ApplicationHelper.isCloudTrashBinFeatureOpen() && SyncUtil.existXiaomiAccount(GalleryApp.sGetAndroidContext())) {
                arrayList4.add(redDotAtom6);
            }
            arrayList4.add(redDotAtom8);
            arrayList.add(new RedDotGroup("action_bar_more", arrayList4, 4));
        }
        flatMap(new RedDotGroup("root", arrayList, 1));
    }

    protected static int getDisplayStatus(boolean z) {
        if (z) {
            getRedDotMap().get("root").processDisplayStatus();
        }
        return sDisplayStatus;
    }

    private static Map<String, RedDot> getRedDotMap() {
        Map<String, RedDot> map = sRedDotMap;
        if (map != null && map.size() != 0) {
            return sRedDotMap;
        }
        generateRedDotMap();
        return sRedDotMap;
    }

    public static boolean getRedDotStatus(String str) {
        return getRedDotStatus(str, true);
    }

    protected static boolean getRedDotStatus(String str, boolean z) {
        return (keyToPosition(str) & getDisplayStatus(z)) != 0;
    }

    private static boolean isRedDotShown(String str) {
        Map<String, RedDot> map = sRedDotMap;
        if (map == null) {
            return false;
        }
        RedDot redDot = map.get(str);
        if (redDot instanceof RedDotAtom) {
            return GalleryPreferences.FeatureRedDot.getRedDotSawTime(str) > 0;
        }
        if (redDot instanceof RedDotGroup) {
            for (RedDot key : ((RedDotGroup) redDot).mPriorityGroup) {
                if (isRedDotShown(key.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int keyToPosition(String str) {
        return sKeyPositionMap.get(str).intValue();
    }

    protected static boolean reddenDot(String str) {
        int keyToPosition = keyToPosition(str);
        if ((sDisplayStatus & keyToPosition) != 0) {
            return true;
        }
        if (isRedDotShown(str)) {
            sDisplayStatus |= keyToPosition;
            return true;
        } else if (System.currentTimeMillis() - GalleryPreferences.FeatureRedDot.getLastUnreddenTime() <= 7200000) {
            return false;
        } else {
            sDisplayStatus |= keyToPosition;
            GalleryPreferences.FeatureRedDot.setLastUnreddenTime(0);
            return true;
        }
    }

    public static void regenerateRedDotMap() {
        if (sRedDotMap != null) {
            generateRedDotMap();
        }
    }

    public static void setRedDotClicked(String str) {
        RedDot redDot = getRedDotMap().get(str);
        if (redDot != null) {
            redDot.onClick();
        } else {
            Log.e(TAG, "get a null red dot from red dot map");
        }
    }

    protected static void unreddenDot(String str) {
        int keyToPosition = keyToPosition(str);
        int i = sDisplayStatus;
        if ((i & keyToPosition) != 0) {
            sDisplayStatus = (~keyToPosition) & i;
            if (GalleryPreferences.FeatureRedDot.getLastUnreddenTime() == 0) {
                GalleryPreferences.FeatureRedDot.setLastUnreddenTime(System.currentTimeMillis());
            }
        }
    }

    public static void updateFeature(String str) {
        getRedDotMap().get(str).onUpdate();
    }
}
