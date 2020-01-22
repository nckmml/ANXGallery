package com.miui.gallery.ui;

import android.view.View;

public interface ViewProvider {
    View getViewByType(int i);

    int getViewResId(int i);
}
