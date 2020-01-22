package com.miui.widget.util;

import android.view.View;

public class ViewUtils {
    public static boolean isLayoutRtl(View view) {
        return view.getLayoutDirection() == 1;
    }
}
