package com.miui.gallery.compat.transition;

import android.os.Build;
import android.transition.Transition;

public class TransitionCompat {
    public static void addTarget(Transition transition, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            transition.addTarget(str);
            return;
        }
        throw new UnsupportedOperationException("not support before v21");
    }
}
