package com.miui.gallery.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.miui.gallery.R;

public class HomePageGridHeaderItem extends TimeLineGridHeaderItem {
    private static Drawable sHeaderBackground;
    private static int sLastNightMode;

    public HomePageGridHeaderItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private static Drawable getHeaderBackground(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        if (sHeaderBackground == null || i != sLastNightMode) {
            sHeaderBackground = context.getDrawable(R.drawable.sticky_header_bg);
            sLastNightMode = i;
        }
        return sHeaderBackground;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        setBackground(getHeaderBackground(getContext()));
    }
}
