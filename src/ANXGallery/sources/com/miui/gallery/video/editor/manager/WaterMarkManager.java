package com.miui.gallery.video.editor.manager;

import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.video.editor.TextStyle;
import com.miui.gallery.video.editor.model.LocalResource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaterMarkManager {
    private static final int[] sBgColor = {R.drawable.video_editor_water_mark_color_1, R.drawable.video_editor_water_mark_color_2, R.drawable.video_editor_water_mark_color_3, R.drawable.video_editor_water_mark_color_4, R.drawable.video_editor_water_mark_color_5, R.drawable.video_editor_water_mark_color_6, R.drawable.video_editor_water_mark_color_7, R.drawable.video_editor_water_mark_color_8};

    private static TextStyle getLocalCustomTextStyle() {
        TextStyle textStyle = new TextStyle(R.drawable.video_editor_water_mark_text, "ve_type_local");
        textStyle.setDownloadState(17);
        return textStyle;
    }

    private static TextStyle getLocalNoneTextStyle() {
        TextStyle textStyle = new TextStyle(R.drawable.video_editor_icon_water_mark_none, "ve_type_none");
        textStyle.setDownloadState(17);
        return textStyle;
    }

    public static ArrayList<TextStyle> initDataWithBgColor() {
        ArrayList<TextStyle> arrayList = new ArrayList<>();
        arrayList.add(getLocalNoneTextStyle());
        arrayList.add(getLocalCustomTextStyle());
        for (int iconResId : sBgColor) {
            TextStyle textStyle = new TextStyle();
            textStyle.setIconResId(iconResId);
            arrayList.add(textStyle);
        }
        return arrayList;
    }

    public static ArrayList<TextStyle> loadWaterMarks(List<LocalResource> list) {
        int i;
        ArrayList<TextStyle> arrayList = new ArrayList<>();
        if (!MiscUtil.isValid(list)) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            LocalResource localResource = list.get(i2);
            if (localResource != null) {
                TextStyle textStyle = new TextStyle(localResource);
                if (textStyle.isNone()) {
                    textStyle.setIconResId(R.drawable.video_editor_icon_water_mark_none);
                } else if (textStyle.isLocal()) {
                    textStyle.setIconResId(R.drawable.video_editor_water_mark_text);
                } else if (textStyle.isExtra()) {
                    int[] iArr = sBgColor;
                    if (i2 < 2 || i2 - 2 >= iArr.length) {
                        i = 0;
                    }
                    textStyle.setBgColor(iArr[i]);
                }
                if (!BuildUtil.isInternational() || localResource.isInternational()) {
                    arrayList.add(textStyle);
                }
            }
        }
        return arrayList;
    }

    public static void updateDataWithTemplate(String[] strArr, TextStyle textStyle) {
        if (strArr != null && textStyle != null) {
            int i = 0;
            while (i < strArr.length) {
                String str = strArr[i];
                if (TextUtils.isEmpty(textStyle.getAssetName()) || !str.contains(textStyle.getAssetName())) {
                    i++;
                } else {
                    textStyle.setDownloadState(0);
                    textStyle.setTemplateId(str);
                    return;
                }
            }
        }
    }

    public void initDataWithTemplate(String[] strArr, ArrayList<TextStyle> arrayList) {
        boolean z;
        if (strArr != null && arrayList != null) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    Iterator<TextStyle> it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        TextStyle next = it.next();
                        if (next != null && !TextUtils.isEmpty(next.getAssetName()) && str.contains(next.getAssetName())) {
                            next.setDownloadState(17);
                            next.setTemplateId(str);
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        NexAssetTemplateManager.getInstance().uninstallPackageById(str);
                    }
                }
            }
        }
    }
}
