package com.miui.gallery.ui;

import android.content.Context;
import android.util.DisplayMetrics;

public class CenterSmoothScrollerController extends SmoothScrollerController {
    public CenterSmoothScrollerController(Context context) {
        super(context);
    }

    public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
        return (i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2));
    }

    /* access modifiers changed from: protected */
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 0.8f;
    }

    public int getVerticalSnapPreference() {
        return -1;
    }
}
