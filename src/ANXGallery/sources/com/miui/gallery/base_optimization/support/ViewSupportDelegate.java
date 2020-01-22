package com.miui.gallery.base_optimization.support;

import android.widget.TextView;

public class ViewSupportDelegate implements IViewSupport {

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static final ViewSupportDelegate INSTANCE = new ViewSupportDelegate();
    }

    private ViewSupportDelegate() {
    }

    public static ViewSupportDelegate getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setText(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }
}
