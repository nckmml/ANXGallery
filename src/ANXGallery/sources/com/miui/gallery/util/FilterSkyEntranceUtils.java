package com.miui.gallery.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.miui.gallery.editor.photo.app.PhotoEditor;

public class FilterSkyEntranceUtils {
    private static final String[] sSingleSkyWhiteList = {"vela"};

    public static boolean showSingleFilterSky() {
        for (String equals : sSingleSkyWhiteList) {
            if (equals.equals(Build.DEVICE)) {
                return true;
            }
        }
        return false;
    }

    public static void startFilterSkyFromPicker(Context context) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("pick-upper-bound", 1);
        Intent intent2 = new Intent(context, PhotoEditor.class);
        intent2.putExtra("editor_mode", 1);
        intent.putExtra("pick_intent", intent2);
        intent.putExtra("pick_close_type", 3);
        intent.setPackage("com.miui.gallery");
        context.startActivity(intent);
    }
}
