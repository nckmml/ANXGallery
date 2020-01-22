package com.miui.gallery.video.editor.manager;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.Filter;
import com.miui.gallery.video.editor.model.FilterAdjustData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterAdjustManager {
    private static int[] filterIcons = {R.drawable.video_editor_filter_origin, R.drawable.video_editor_filter_jiaopian, R.drawable.video_editor_filter_jingmi, R.drawable.video_editor_filter_wangshi, R.drawable.video_editor_filter_nuancha, R.drawable.video_editor_filter_bailu, R.drawable.video_editor_filter_qingse, R.drawable.video_editor_filter_xiaosenlin, R.drawable.video_editor_filter_heibai};
    private static HashMap<Integer, String> sAdjustMap = new HashMap<Integer, String>() {
        {
            put(0, "曝光度");
            put(3, "清晰度");
            put(1, "对比度");
            put(2, "饱和度");
            put(4, "暗角");
        }
    };
    private static String[] sFilterName = {"原图", "胶片", "静谧", "往事", "暖茶", "白露", "青涩", "小森林", "黑白"};
    private static String[] sFilterNameKeys = {"video_editor_filter_origin", "video_editor_filter_jiaopian", "video_editor_filter_jingmi", "video_editor_filter_wangshi", "video_editor_filter_nuancha", "video_editor_filter_bailu", "video_editor_filter_qingse", "video_editor_filter_xiaosenlin", "video_editor_filter_heibai"};
    private static String[] sFilterTypes = {"ve_type_none", "ve_type_local", "ve_type_local", "ve_type_local", "ve_type_local", "ve_type_local", "ve_type_local", "ve_type_local", "ve_type_local"};

    public static List<FilterAdjustData> getAdjustData() {
        return Arrays.asList(new FilterAdjustData[]{new FilterAdjustData(0, 10, GalleryApp.sGetAndroidContext().getString(R.string.adjust_brightness), R.drawable.adjust_brightness, true, sAdjustMap.get(0)), new FilterAdjustData(3, 10, GalleryApp.sGetAndroidContext().getString(R.string.adjust_sharpen), R.drawable.adjust_sharpen, false, sAdjustMap.get(3)), new FilterAdjustData(1, 10, GalleryApp.sGetAndroidContext().getString(R.string.adjust_contrast), R.drawable.adjust_contrast, true, sAdjustMap.get(1)), new FilterAdjustData(2, 10, GalleryApp.sGetAndroidContext().getString(R.string.adjust_saturation), R.drawable.adjust_saturation, true, sAdjustMap.get(2)), new FilterAdjustData(4, 10, GalleryApp.sGetAndroidContext().getString(R.string.adjust_vignette), R.drawable.adjust_vignette, false, sAdjustMap.get(4))});
    }

    public static ArrayList<Filter> getFilterData() {
        ArrayList<Filter> arrayList = new ArrayList<>();
        int i = 0;
        while (true) {
            String[] strArr = sFilterNameKeys;
            if (i >= strArr.length) {
                return arrayList;
            }
            arrayList.add(new Filter(filterIcons[i], sFilterTypes[i], strArr[i], sFilterName[i]));
            i++;
        }
    }
}
