package com.miui.gallery.util;

import android.view.View;
import android.view.ViewParent;

public class ViewUtils {
    public static void setRootViewClickable(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent == null || !(parent instanceof View)) {
                view.setClickable(true);
            } else {
                ((View) parent).setClickable(true);
            }
        }
    }
}
